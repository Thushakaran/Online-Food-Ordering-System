package com.se.Online.Food.Ordering.System.controller;

import com.se.Online.Food.Ordering.System.dto.FoodItemDTO;
import com.se.Online.Food.Ordering.System.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @GetMapping("/list")
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDTO> getFoodItem(@PathVariable Long id) {
        return ResponseEntity.ok(foodItemService.getFoodItemById(id));
    }
}
