package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.FoodItemDTO;
import com.se.Online.Food.Ordering.System.dto.FoodItemRequestDTO;

import java.util.List;


public interface FoodItemService {
    List<FoodItemDTO> getAllFoodItems();

    FoodItemDTO getFoodItemById(Long id);

    FoodItemDTO addFoodItem(FoodItemRequestDTO foodItemRequest);

    FoodItemDTO updateFoodItem(Long id, FoodItemRequestDTO foodItemRequest);

    void deleteFoodItem(Long id);
}
