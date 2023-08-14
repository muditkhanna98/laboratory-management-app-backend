package com.humber.laboratorymgntappbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resultId;

    @ManyToOne
    @JoinColumn(name = "test_order_id")
    private TestOrder testOrder;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;

    private String resultText;

}
