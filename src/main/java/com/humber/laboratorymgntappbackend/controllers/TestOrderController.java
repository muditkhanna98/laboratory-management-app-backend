package com.humber.laboratorymgntappbackend.controllers;

import com.humber.laboratorymgntappbackend.models.TestOrder;
import com.humber.laboratorymgntappbackend.models.TestResult;
import com.humber.laboratorymgntappbackend.services.TestOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/test-orders")
public class TestOrderController {

    @Autowired
    TestOrderService testOrderService;

    @PostMapping
    public ResponseEntity<?> createTestOrder(@RequestBody TestOrder testOrder) {
        try {
            TestOrder createdOrder = testOrderService.createTestOrder(testOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TestOrder>> getAllTestResults() {
        List<TestOrder> allTestOrders = testOrderService.getAllTestOrders();
        return ResponseEntity.ok(allTestOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestOrder> getTestOrder(@PathVariable int id) {
        TestOrder order = testOrderService.getTestOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTestOrderStatus(@PathVariable int id, @RequestBody TestOrder updatedTestOrder) {
        try {
            TestOrder updatedOrder = testOrderService.updateTestOrderStatus(id, updatedTestOrder);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
