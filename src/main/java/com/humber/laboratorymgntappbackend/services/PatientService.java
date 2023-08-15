package com.humber.laboratorymgntappbackend.services;

import com.humber.laboratorymgntappbackend.models.Patient;
import com.humber.laboratorymgntappbackend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(int patientId, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));

        existingPatient.setFirstName(updatedPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName());
        existingPatient.setDob(updatedPatient.getDob());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setContactNumber(updatedPatient.getContactNumber());

        return patientRepository.save(existingPatient);
    }

    public Patient searchByPatientId(int patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        return patientOptional.orElse(null);
    }

    // New combined method
    public List<Patient> searchPatientsByFirstNameOrLastName(String name) {
        return patientRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name);
    }
}
