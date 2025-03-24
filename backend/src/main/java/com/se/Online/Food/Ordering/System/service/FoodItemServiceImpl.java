//package com.se.Online.Food.Ordering.System.service;
//
//import com.se.Online.Food.Ordering.System.dto.CategoryDTO;
//import com.se.Online.Food.Ordering.System.dto.FoodItemDTO;
//import com.se.Online.Food.Ordering.System.dto.FoodItemRequestDTO;
//import com.se.Online.Food.Ordering.System.exception.ResourceNotFoundException;
//import com.se.Online.Food.Ordering.System.model.Category;
//import com.se.Online.Food.Ordering.System.model.FoodItem;
//import com.se.Online.Food.Ordering.System.model.Restaurant;
//import com.se.Online.Food.Ordering.System.repository.CategoryRepository;
//import com.se.Online.Food.Ordering.System.repository.FoodItemRepository;
//import com.se.Online.Food.Ordering.System.repository.RestaurantRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FoodItemServiceImpl implements FoodItemService {
//    @Autowired
//    private FoodItemRepository foodItemRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private AdminRepository adminRepository;
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    @Override
//    public List<FoodItemDTO> getAllFoodItems() {
//        List<FoodItem> foodItems = foodItemRepository.findAll();
//        return foodItems.stream()
//                .map(foodItem -> new FoodItemDTO(
//                        foodItem.getId(),
//                        foodItem.getName(),
//                        foodItem.getPrice(),
//                        new CategoryDTO(foodItem.getCategory().getId(), foodItem.getCategory().getName(), foodItem.getCategory().getDescription()),
//                        foodItem.getAdmin().getId(),
//                        foodItem.getRestaurant().getId()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public FoodItemDTO getFoodItemById(Long id) {
//        FoodItem foodItem = foodItemRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));
//        return new FoodItemDTO(
//                foodItem.getId(),
//                foodItem.getName(),
//                foodItem.getPrice(),
//                new CategoryDTO(foodItem.getCategory().getId(), foodItem.getCategory().getName(), foodItem.getCategory().getDescription()),
//                foodItem.getAdmin().getId(),
//                foodItem.getRestaurant().getId());
//    }
//
//    @Override
//    public FoodItemDTO addFoodItem(FoodItemRequestDTO foodItemRequest) {
//        Category category = categoryRepository.findById(foodItemRequest.getCategoryId())
//                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", foodItemRequest.getCategoryId()));
//
//        Admin admin = adminRepository.findById(foodItemRequest.getAdminId())
//                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", foodItemRequest.getAdminId()));
//
//        Restaurant restaurant = restaurantRepository.findById(foodItemRequest.getRestaurantId())
//                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", foodItemRequest.getRestaurantId()));
//
//        FoodItem foodItem = new FoodItem();
//        foodItem.setName(foodItemRequest.getName());
//        foodItem.setPrice(foodItemRequest.getPrice());
//        foodItem.setCategory(category);
//        foodItem.setAdmin(admin);
//        foodItem.setRestaurant(restaurant);
//
//        FoodItem savedFoodItem = foodItemRepository.save(foodItem);
//        return getFoodItemById(savedFoodItem.getId());
//    }
//
//    @Override
//    public FoodItemDTO updateFoodItem(Long id, FoodItemRequestDTO foodItemRequest) {
//        FoodItem foodItem = foodItemRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));
//
//        foodItem.setName(foodItemRequest.getName());
//        foodItem.setPrice(foodItemRequest.getPrice());
//
//        Category category = categoryRepository.findById(foodItemRequest.getCategoryId())
//                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", foodItemRequest.getCategoryId()));
//        foodItem.setCategory(category);
//
//        FoodItem updatedFoodItem = foodItemRepository.save(foodItem);
//        return getFoodItemById(updatedFoodItem.getId());
//    }
//
//    @Override
//    public void deleteFoodItem(Long id) {
//        if (!foodItemRepository.existsById(id)) {
//            throw new ResourceNotFoundException("FoodItem", "id", id);
//        }
//        foodItemRepository.deleteById(id);
//    }
//}

package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.CategoryDTO;
import com.se.Online.Food.Ordering.System.dto.FoodItemDTO;
import com.se.Online.Food.Ordering.System.dto.FoodItemRequestDTO;
import com.se.Online.Food.Ordering.System.exception.ResourceNotFoundException;
import com.se.Online.Food.Ordering.System.model.*;
import com.se.Online.Food.Ordering.System.repository.CategoryRepository;
import com.se.Online.Food.Ordering.System.repository.FoodItemRepository;
import com.se.Online.Food.Ordering.System.repository.RestaurantRepository;
import com.se.Online.Food.Ordering.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository; // Changed from AdminRepository
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        return foodItems.stream()
                .map(foodItem -> new FoodItemDTO(
                        foodItem.getId(),
                        foodItem.getName(),
                        foodItem.getPrice(),
                        new CategoryDTO(foodItem.getCategory().getId(), foodItem.getCategory().getName(), foodItem.getCategory().getDescription()),
                        foodItem.getUser().getId(), // Changed from foodItem.getAdmin().getId()
                        foodItem.getRestaurant().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public FoodItemDTO getFoodItemById(Long id) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));
        return new FoodItemDTO(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getPrice(),
                new CategoryDTO(foodItem.getCategory().getId(), foodItem.getCategory().getName(), foodItem.getCategory().getDescription()),
                foodItem.getUser().getId(), // Changed from foodItem.getAdmin().getId()
                foodItem.getRestaurant().getId());
    }

    @Override
    public FoodItemDTO addFoodItem(FoodItemRequestDTO foodItemRequest) {
        Category category = categoryRepository.findById(foodItemRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", foodItemRequest.getCategoryId()));

        User user = userRepository.findById(foodItemRequest.getUserId()) // Changed from Admin to User
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", foodItemRequest.getUserId()));

        // Ensure only an Admin can add food items
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("Only Admins can add food items");
        }

        Restaurant restaurant = restaurantRepository.findById(foodItemRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", foodItemRequest.getRestaurantId()));

        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemRequest.getName());
        foodItem.setPrice(foodItemRequest.getPrice());
        foodItem.setCategory(category);
        foodItem.setUser(user); // Changed from setAdmin(admin);
        foodItem.setRestaurant(restaurant);

        FoodItem savedFoodItem = foodItemRepository.save(foodItem);
        return getFoodItemById(savedFoodItem.getId());
    }

    @Override
    public FoodItemDTO updateFoodItem(Long id, FoodItemRequestDTO foodItemRequest) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));

        foodItem.setName(foodItemRequest.getName());
        foodItem.setPrice(foodItemRequest.getPrice());

        Category category = categoryRepository.findById(foodItemRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", foodItemRequest.getCategoryId()));
        foodItem.setCategory(category);

        FoodItem updatedFoodItem = foodItemRepository.save(foodItem);
        return getFoodItemById(updatedFoodItem.getId());
    }

    @Override
    public void deleteFoodItem(Long id) {
        if (!foodItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("FoodItem", "id", id);
        }
        foodItemRepository.deleteById(id);
    }
}
