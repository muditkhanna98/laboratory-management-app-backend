package com.humber.laboratorymgntappbackend.repositories;

import com.humber.laboratorymgntappbackend.models.TestOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestOrderRepository extends JpaRepository<TestOrder, Integer> {

}
