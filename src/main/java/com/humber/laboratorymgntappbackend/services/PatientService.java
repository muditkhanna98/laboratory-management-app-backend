package com.humber.laboratorymgntappbackend.services;

import com.humber.laboratorymgntappbackend.models.Patient;
import com.humber.laboratorymgntappbackend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Patient> addPatient(Patient patient) {
         patientRepository.save(patient);
         return this.getAllPatients();
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

}
