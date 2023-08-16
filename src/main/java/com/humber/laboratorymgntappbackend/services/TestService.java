package com.humber.laboratorymgntappbackend.services;

import com.humber.laboratorymgntappbackend.models.Test;
import com.humber.laboratorymgntappbackend.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests() {
        return this.testRepository.findAll();
    }
}
