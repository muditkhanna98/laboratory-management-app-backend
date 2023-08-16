package com.humber.laboratorymgntappbackend.controllers;

import com.humber.laboratorymgntappbackend.models.Test;
import com.humber.laboratorymgntappbackend.services.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getAllTest() {
        return this.testService.getAllTests();
    }
}
