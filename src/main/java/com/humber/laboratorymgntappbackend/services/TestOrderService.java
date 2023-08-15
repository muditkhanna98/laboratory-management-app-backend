package com.humber.laboratorymgntappbackend.services;

import com.humber.laboratorymgntappbackend.models.*;
import com.humber.laboratorymgntappbackend.repositories.PatientRepository;
import com.humber.laboratorymgntappbackend.repositories.TestOrderRepository;
import com.humber.laboratorymgntappbackend.repositories.TestRepository;
import com.humber.laboratorymgntappbackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class TestOrderService {

    @Autowired
    TestOrderRepository testOrderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    PatientRepository patientRepository;

    public TestOrder createTestOrder(TestOrder testOrder) throws AccessDeniedException {
        Test test = testOrder.getTest();
        Patient patient = testOrder.getPatient();
        User medicalUser = testOrder.getMedical_user();

        // Check if the Test, Patient, and User entities exist in the database
        test = testRepository.findById(test.getTestId())
                .orElseThrow(() -> new EntityNotFoundException("Test not found"));
        patient = patientRepository.findById(patient.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        medicalUser = userRepository.findById(medicalUser.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Medical User not found"));
        if (medicalUser.getRole().equals("technician") ) throw new AccessDeniedException("Technicians are not allowed to create test orders.");

        // Associate the retrieved entities with the TestResult
        testOrder.setTest(test);
        testOrder.setPatient(patient);
        testOrder.setMedical_user(medicalUser);

        return testOrderRepository.save(testOrder);
    }


    public List<TestOrder> getAllTestOrders() {
        return testOrderRepository.findAll();
    }

    public TestOrder getTestOrderById(int id) {
        return testOrderRepository.findById(id).orElse(null);
    }

    public TestOrder updateTestOrderStatus(int id, TestOrder updatedTestOrder) {
        TestOrder existingTestOrder = testOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Test Order not found"));

        // Update the status field of the existingTestOrder with values from updatedTestOrder
        existingTestOrder.setStatus(updatedTestOrder.getStatus());

        // Save the updated test order
        return testOrderRepository.save(existingTestOrder);
    }



}
