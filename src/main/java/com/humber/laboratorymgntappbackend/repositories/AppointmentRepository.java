package com.humber.laboratorymgntappbackend.repositories;

import com.humber.laboratorymgntappbackend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
