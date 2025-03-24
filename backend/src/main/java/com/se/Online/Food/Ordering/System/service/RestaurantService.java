package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO);

    void deleteRestaurant(Long id);

    List<RestaurantDTO> getAllRestaurants();

    RestaurantDTO getRestaurantById(Long id);
}
