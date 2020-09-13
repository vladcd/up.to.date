package com.vladcarcu.up.to.date.appointment;

import com.vladcarcu.up.to.date.common.mappers.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentResource {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    @PostMapping
    public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toAppointment(appointmentDTO);
        return appointmentMapper.toDTO(appointmentService.createAppointment(appointment));
    }

    @GetMapping("/client/{id}")
    public List<AppointmentDTO> getClientAppointments(@PathVariable("id") Integer userId) {
        return appointmentService.upcomingAppointments(userId).stream()
                .map(appointmentMapper::toDTO)
                .collect(toList());
    }

    @GetMapping("/doctor/{id}")
    public List<AppointmentDTO> getAppointments(@PathVariable("id") Integer doctorId) {
        return appointmentService.upcomingDoctorAppointments(doctorId).stream()
                .map(appointmentMapper::toDTO)
                .collect(toList());
    }

    // TODO: TOP 8
    @GetMapping("/doctor/{id}/nextslot")
    public AppointmentDTO getNextAvailableSlot(@PathVariable("id") Integer doctorId) {
        return appointmentService.nextFreeSlot(doctorId, LocalDateTime.now())
                .map(appointmentMapper::toDTO)
                .orElseThrow(NoSuchElementException::new);
    }
}
