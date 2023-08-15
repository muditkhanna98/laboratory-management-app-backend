package com.humber.laboratorymgntappbackend.services;

import com.humber.laboratorymgntappbackend.models.TestOrder;
import com.humber.laboratorymgntappbackend.models.TestResult;
import com.humber.laboratorymgntappbackend.models.User;
import com.humber.laboratorymgntappbackend.repositories.TestOrderRepository;
import com.humber.laboratorymgntappbackend.repositories.TestResultRepository;
import com.humber.laboratorymgntappbackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TestResultService {

    @Autowired
    TestResultRepository testResultRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestOrderRepository testOrderRepository;

    public TestResult uploadTestResult(TestResult testResult) throws AccessDeniedException {
        TestOrder testOrder = testResult.getTestOrder();
        User technician = testResult.getTechnician();

        // Check if the TestOrder and User entities exist in the database
        testOrder = testOrderRepository.findById(testOrder.getTestOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Test Order not found"));
        technician = userRepository.findById(technician.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Technician not found"));
        if (!technician.getRole().equals("technician") ) throw new AccessDeniedException("Only technicians are allowed to upload test results.");

        // Associate the retrieved entities with the TestResult
        testResult.setTestOrder(testOrder);
        testResult.setTechnician(technician);

        return testResultRepository.save(testResult);

    }

    public List<TestResult> getAllTestResults() {
        return testResultRepository.findAll();
    }

    public TestResult getTestResultById(int id) {
        return testResultRepository.findById(id).orElse(null);
    }
	

}
