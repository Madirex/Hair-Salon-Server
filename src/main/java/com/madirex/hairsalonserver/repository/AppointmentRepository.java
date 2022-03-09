package com.madirex.hairsalonserver.repository;

import com.madirex.hairsalonserver.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByDateAndTimeAndService_Id(LocalDate date, LocalTime time, String serviceId);
}
