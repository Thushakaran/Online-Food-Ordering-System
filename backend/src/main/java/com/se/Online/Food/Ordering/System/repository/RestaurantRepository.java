package com.se.Online.Food.Ordering.System.repository;

import com.se.Online.Food.Ordering.System.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);
    
    // Custom query if needed to find by admin ID
    List<Restaurant> findByAdminId(Long adminId);
}
