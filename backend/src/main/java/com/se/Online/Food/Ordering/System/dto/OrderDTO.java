package com.se.Online.Food.Ordering.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String status;
    private double totalPrice;
    private Long userId;
    private Long restaurantId;
    private List<Long> foodItemIds; // Only IDs to avoid unnecessary data loading
}
