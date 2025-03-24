package com.se.Online.Food.Ordering.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String imageUrl;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Admin who owns this restaurant
}

