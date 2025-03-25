package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.RestaurantDTO;
import com.se.Online.Food.Ordering.System.exception.ResourceNotFoundException;
import com.se.Online.Food.Ordering.System.model.Restaurant;
import com.se.Online.Food.Ordering.System.model.Role;
import com.se.Online.Food.Ordering.System.model.User;
import com.se.Online.Food.Ordering.System.repository.FoodItemRepository;
import com.se.Online.Food.Ordering.System.repository.RestaurantRepository;
import com.se.Online.Food.Ordering.System.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        User user = userRepository.findById(restaurantDTO.getUserId()) // Changed from Admin to User
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", restaurantDTO.getUserId()));

        // Ensure only an Admin can add food items
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("Only Admins can add food items");
        }

        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        restaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    @Override
    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        User user = userRepository.findById(restaurantDTO.getUserId()) // Changed from Admin to User
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", restaurantDTO.getUserId()));

        // Ensure only an Admin can add food items
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("Only Admins can add food items");
        }

        existingRestaurant.setName(restaurantDTO.getName());
        existingRestaurant.setLocation(restaurantDTO.getLocation());
        existingRestaurant.setImageUrl(restaurantDTO.getImageUrl()); // Set the imageUrl
        existingRestaurant.setUser(user);

        existingRestaurant = restaurantRepository.save(existingRestaurant);
        return modelMapper.map(existingRestaurant, RestaurantDTO.class);
    }

    @Override
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));

        // Delete all associated food items
        foodItemRepository.deleteByRestaurantId(id); // Custom method to delete food items

        // Now delete the restaurant
        restaurantRepository.deleteById(id);
    }


    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }
}
