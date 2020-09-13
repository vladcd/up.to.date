package com.vladcarcu.up.to.date.appointment;

import com.vladcarcu.up.to.date.common.model.User;
import com.vladcarcu.up.to.date.doctor.Doctor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime startDate;

}
