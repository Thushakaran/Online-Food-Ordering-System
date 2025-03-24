package com.se.Online.Food.Ordering.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentMethod;
    private double amount;
    private String status; // e.g., SUCCESS, FAILURE

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
