package com.humber.laboratorymgntappbackend.controllers;

import com.humber.laboratorymgntappbackend.models.TestResult;
import com.humber.laboratorymgntappbackend.services.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/test-results")
public class TestResultController {

    @Autowired
    TestResultService testResultService;

    @PostMapping
    public ResponseEntity<?> uploadTestResult(@RequestBody TestResult testResult) {
        try {
            TestResult uploadedResult = testResultService.uploadTestResult(testResult);
            return ResponseEntity.status(HttpStatus.CREATED).body(uploadedResult);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TestResult>> getAllTestResults() {
        List<TestResult> allResults = testResultService.getAllTestResults();
        return ResponseEntity.ok(allResults);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResult> getTestResult(@PathVariable int id) {
        TestResult result = testResultService.getTestResultById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
