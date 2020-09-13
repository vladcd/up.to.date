package com.vladcarcu.up.to.date.appointment;

import com.vladcarcu.up.to.date.client.ClientDTO;
import com.vladcarcu.up.to.date.doctor.DoctorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Integer id;
    private ClientDTO client;
    private DoctorDTO doctor;
    private LocalDateTime startDate;
}
