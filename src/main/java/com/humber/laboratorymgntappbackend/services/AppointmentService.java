package com.humber.laboratorymgntappbackend.services;

import com.humber.laboratorymgntappbackend.models.Appointment;
import com.humber.laboratorymgntappbackend.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    public Appointment updateAppointment(int appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));

        // Update appointment fields as needed
        existingAppointment.setAppointmentDatetime(updatedAppointment.getAppointmentDatetime());
        existingAppointment.setStatus(updatedAppointment.getStatus());

        return appointmentRepository.save(existingAppointment);
    }
    public Appointment searchByAppointmentId(int appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        return appointmentOptional.orElse(null);
    }
}
