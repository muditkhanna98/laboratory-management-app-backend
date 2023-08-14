package com.humber.laboratorymgntappbackend.repositories;

import com.humber.laboratorymgntappbackend.models.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

}
