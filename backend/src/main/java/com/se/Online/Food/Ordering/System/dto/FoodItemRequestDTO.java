package com.se.Online.Food.Ordering.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemRequestDTO {
    private String name;
    private double price;
    private Long categoryId;
    private Long restaurantId;
    private Long userId;
}
