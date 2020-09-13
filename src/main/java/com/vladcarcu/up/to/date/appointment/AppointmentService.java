package com.vladcarcu.up.to.date.appointment;

import com.vladcarcu.up.to.date.client.Client;
import com.vladcarcu.up.to.date.client.ClientService;
import com.vladcarcu.up.to.date.common.exceptions.DuplicateItemException;
import com.vladcarcu.up.to.date.doctor.Doctor;
import com.vladcarcu.up.to.date.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    public static final int START_HOUR = 9;
    public static final int MAX_SLOTS = 16;
    public static final int MINUTES_SLOT = 30;
    public static final int DAYS_AHEAD = 7;
    public static final int MAX_SLOTS_PER_WEEK = 5 * MAX_SLOTS;

    private final ClientService clientService;
    private final DoctorService doctorService;
    private final AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) {
        appointment.setId(null);
        // check to see whether the appointment is still available
        Optional<Appointment> existingAppointment = appointmentRepository.findByDoctorAndStartDate(appointment.getDoctor(), appointment.getStartDate());
        if (existingAppointment.isPresent()) {
            throw new DuplicateItemException();
        }
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> upcomingAppointments(Integer clientId) {
        Client client = clientService.findById(clientId).orElseThrow(NoSuchElementException::new);
        LocalDateTime limit = LocalDateTime.now().plusWeeks(1L).with(LocalTime.MAX);
        return appointmentRepository.upcomingAppointments(client, limit);
    }

    public List<Appointment> upcomingDoctorAppointments(Integer doctorId) {
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(NoSuchElementException::new);
        LocalDateTime limit = LocalDateTime.now().plusWeeks(1L).with(LocalTime.MAX);

        List<Appointment> asClient = this.upcomingAppointments(doctorId);
        List<Appointment> asDoctor = appointmentRepository.upcomingDoctorAppointments(doctor, limit);
        List<Appointment> result = new ArrayList<>(asClient);
        result.addAll(asDoctor);
        return result.stream()
                .sorted(Comparator.comparing(Appointment::getStartDate))
                .collect(Collectors.toList());
    }

    // TODO: TOP 5
    public Optional<Appointment> nextFreeSlot(Integer doctorId, LocalDateTime initialDate) {
        // the doctor will work with 30 minute slots, from 9 to 5, Monday to Friday
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(NoSuchElementException::new);
        List<LocalDateTime> possibleDates = buildPossibleSlots(initialDate);
        List<Appointment> upcomingAppointments = upcomingAppointments(doctorId);

        return possibleDates.stream()
                .filter(slotIsAvailablePredicate(upcomingAppointments))
                .findFirst()
                .map(localDateTime -> {
                    Appointment appointment = new Appointment();
                    appointment.setDoctor(doctor);
                    appointment.setStartDate(localDateTime);
                    return appointment;
                });
    }

    @Scheduled(cron = "0 0 1 ? * MON")
    public void updateDoctorPopularity() {
        LocalDateTime startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime startOfNextWeek = startOfWeek.plusWeeks(1L);
        List<Appointment> pastWeekAppointments = appointmentRepository.findByStartDateGreaterThanEqualAndStartDateLessThan(startOfWeek, startOfNextWeek);
        List<Doctor> updatedDoctors = pastWeekAppointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDoctor))
                .entrySet()
                .stream()
                .map(doctorAppointments -> {
                    Doctor doctor = doctorAppointments.getKey();
                    List<Appointment> appointments = doctorAppointments.getValue();
                    doctor.setPopularity(doctorService.calculatePopularity(appointments.size(), MAX_SLOTS_PER_WEEK));
                    return doctor;
                }).collect(Collectors.toList());
        doctorService.updateAll(updatedDoctors);
    }

    private Predicate<LocalDateTime> slotIsAvailablePredicate(List<Appointment> upcomingAppointments) {
        return possibleDate ->
                upcomingAppointments.stream().noneMatch(appointment -> appointment.getStartDate().equals(possibleDate));
    }

    private List<LocalDateTime> buildPossibleSlots(LocalDateTime initialDate) {
        List<LocalDateTime> result = new ArrayList<>();
        for (int dayOffset = 0; dayOffset < DAYS_AHEAD; dayOffset++) {
            LocalDateTime day = initialDate.plusDays(dayOffset);
            if (day.getDayOfWeek().equals(DayOfWeek.SATURDAY) || day.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                continue;
            }
            if (dayOffset != 0) {
                day = day.withHour(START_HOUR).withMinute(0).withSecond(0).withNano(0);
            }
            for (int slotOffset = 0; slotOffset < MAX_SLOTS; slotOffset++) {
                result.add(day.plusMinutes(slotOffset * MINUTES_SLOT));
            }
        }
        return result;
    }
}
