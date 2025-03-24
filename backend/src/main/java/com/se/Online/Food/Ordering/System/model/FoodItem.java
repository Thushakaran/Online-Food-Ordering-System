package com.se.Online.Food.Ordering.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "foodItem")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id") // the column that joins to the Admin table
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // the column that links to the Restaurant table
    private Restaurant restaurant;
}
