package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.OrderDTO;
import com.se.Online.Food.Ordering.System.model.Order;
import com.se.Online.Food.Ordering.System.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setStatus(orderDTO.getStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());

        // Convert DTOs to entities (e.g., foodItems, user, etc.)
        // This can be improved with logic to map the food items and user correctly
        order = orderRepository.save(order);

        return new OrderDTO(order.getId(), order.getStatus(), order.getTotalPrice(), new ArrayList<>());
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> new OrderDTO(order.getId(), order.getStatus(), order.getTotalPrice(), new ArrayList<>()))
                .collect(Collectors.toList());
    }
}
