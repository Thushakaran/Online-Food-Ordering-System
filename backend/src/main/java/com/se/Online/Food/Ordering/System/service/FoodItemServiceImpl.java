package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.CategoryDTO;
import com.se.Online.Food.Ordering.System.dto.FoodItemDTO;
import com.se.Online.Food.Ordering.System.exception.ResourceNotFoundException;
import com.se.Online.Food.Ordering.System.model.FoodItem;
import com.se.Online.Food.Ordering.System.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItemDTO> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        return foodItems.stream()
                .map(foodItem -> new FoodItemDTO(foodItem.getId(), foodItem.getName(), foodItem.getPrice(),
                        new CategoryDTO(foodItem.getCategory().getId(), foodItem.getCategory().getName())))
                .collect(Collectors.toList());
    }

    public FoodItemDTO getFoodItemById(Long id) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));
        return new FoodItemDTO(foodItem.getId(), foodItem.getName(), foodItem.getPrice(),
                new CategoryDTO(foodItem.getCategory().getId(), foodItem.getCategory().getName()));
    }
}
