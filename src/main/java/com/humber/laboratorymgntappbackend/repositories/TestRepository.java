package com.humber.laboratorymgntappbackend.repositories;

import com.humber.laboratorymgntappbackend.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {

}
