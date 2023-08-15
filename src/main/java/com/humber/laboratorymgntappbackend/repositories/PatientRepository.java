package com.humber.laboratorymgntappbackend.repositories;

import com.humber.laboratorymgntappbackend.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // Custom queries can be defined here if needed
    List<Patient> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);
}
