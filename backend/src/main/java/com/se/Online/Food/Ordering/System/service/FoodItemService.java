package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.FoodItemDTO;

import java.util.List;


public interface FoodItemService {
    List<FoodItemDTO> getAllFoodItems();

    FoodItemDTO getFoodItemById(Long id);
}
