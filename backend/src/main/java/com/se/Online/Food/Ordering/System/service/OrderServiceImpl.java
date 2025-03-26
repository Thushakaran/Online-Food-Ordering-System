package com.se.Online.Food.Ordering.System.service.impl;

import com.se.Online.Food.Ordering.System.dto.OrderDTO;
import com.se.Online.Food.Ordering.System.model.FoodItem;
import com.se.Online.Food.Ordering.System.model.Order;
import com.se.Online.Food.Ordering.System.model.Restaurant;
import com.se.Online.Food.Ordering.System.model.User;
import com.se.Online.Food.Ordering.System.repository.FoodItemRepository;
import com.se.Online.Food.Ordering.System.repository.OrderRepository;
import com.se.Online.Food.Ordering.System.repository.RestaurantRepository;
import com.se.Online.Food.Ordering.System.repository.UserRepository;
import com.se.Online.Food.Ordering.System.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodItemRepository foodItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                            RestaurantRepository restaurantRepository, FoodItemRepository foodItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setStatus(orderDTO.getStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        Restaurant restaurant = restaurantRepository.findById(orderDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        order.setRestaurant(restaurant);

        List<FoodItem> foodItems = foodItemRepository.findAllById(orderDTO.getFoodItemIds());
        order.setFoodItems(foodItems);

        order = orderRepository.save(order);
        return mapToDTO(order);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(orderDTO.getStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        Restaurant restaurant = restaurantRepository.findById(orderDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        order.setRestaurant(restaurant);

        List<FoodItem> foodItems = foodItemRepository.findAllById(orderDTO.getFoodItemIds());
        order.setFoodItems(foodItems);

        order = orderRepository.save(order);
        return mapToDTO(order);
    }


    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getUser().getId(),
                order.getRestaurant().getId(),
                order.getFoodItems().stream().map(FoodItem::getId).collect(Collectors.toList())
        );
    }
}
