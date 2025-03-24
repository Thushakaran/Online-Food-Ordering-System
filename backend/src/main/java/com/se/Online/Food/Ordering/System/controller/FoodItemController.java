package com.se.Online.Food.Ordering.System.controller;

import com.se.Online.Food.Ordering.System.dto.FoodItemDTO;
import com.se.Online.Food.Ordering.System.dto.FoodItemRequestDTO;
import com.se.Online.Food.Ordering.System.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDTO> getFoodItem(@PathVariable Long id) {
        return ResponseEntity.ok(foodItemService.getFoodItemById(id));
    }

    @PostMapping
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemRequestDTO foodItemRequest) {
        return ResponseEntity.ok(foodItemService.addFoodItem(foodItemRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemDTO> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemRequestDTO foodItemRequest) {
        return ResponseEntity.ok(foodItemService.updateFoodItem(id, foodItemRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.noContent().build();
    }
}
