package com.vladcarcu.up.to.date.common.mappers;

import com.vladcarcu.up.to.date.appointment.Appointment;
import com.vladcarcu.up.to.date.appointment.AppointmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    private final ClientMapper clientMapper;
    private final DoctorMapper doctorMapper;

    public AppointmentDTO toDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .client(clientMapper.toDTO(appointment.getClient()))
                .doctor(doctorMapper.toDTO(appointment.getDoctor()))
                .startDate(appointment.getStartDate())
                .build();
    }

    public Appointment toAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());
        appointment.setStartDate(appointmentDTO.getStartDate());
        appointment.setDoctor(doctorMapper.toDoctor(appointmentDTO.getDoctor()));
        appointment.setClient(clientMapper.toClient(appointmentDTO.getClient()));
        return appointment;
    }
}
