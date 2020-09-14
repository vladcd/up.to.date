package com.vladcarcu.up.to.date.appointment;

import com.vladcarcu.up.to.date.common.model.User;
import com.vladcarcu.up.to.date.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // TODO: TOP 3
    @Query("""
            select a 
            from Appointment a 
            where a.client=:client 
            and a.startDate > current_timestamp 
            and a.startDate <= :limit
            """)
    List<Appointment> upcomingAppointments(User client, LocalDateTime limit);

    @Query("select a from Appointment a where a.doctor=:doctor and a.startDate > current_timestamp and a.startDate <= :limit")
    List<Appointment> upcomingDoctorAppointments(Doctor doctor, LocalDateTime limit);

    Optional<Appointment> findByDoctorAndStartDate(Doctor doctor, LocalDateTime startDate);

    List<Appointment> findByStartDateGreaterThanEqualAndStartDateLessThan(LocalDateTime startOfWeek, LocalDateTime endOfWeek);

}
