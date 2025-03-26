package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.OrderItemDto;
import com.se.Online.Food.Ordering.System.model.FoodItem;
import com.se.Online.Food.Ordering.System.model.Order;
import com.se.Online.Food.Ordering.System.model.OrderItem;
import com.se.Online.Food.Ordering.System.repository.FoodItemRepository;
import com.se.Online.Food.Ordering.System.repository.OrderItemRepository;
import com.se.Online.Food.Ordering.System.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final FoodItemRepository foodItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, FoodItemRepository foodItemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        Order order = orderRepository.findById(orderItemDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        FoodItem foodItem = foodItemRepository.findById(orderItemDto.getFoodItemId())
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setOrder(order);
        orderItem.setFoodItem(foodItem);

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return mapToDto(savedOrderItem);
    }

    @Override
    public OrderItemDto getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
        return mapToDto(orderItem);
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        orderItem.setQuantity(orderItemDto.getQuantity());

        Order order = orderRepository.findById(orderItemDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderItem.setOrder(order);

        FoodItem foodItem = foodItemRepository.findById(orderItemDto.getFoodItemId())
                .orElseThrow(() -> new RuntimeException("FoodItem not found"));
        orderItem.setFoodItem(foodItem);

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        return mapToDto(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
        orderItemRepository.delete(orderItem);
    }

    private OrderItemDto mapToDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getOrder().getId(),
                orderItem.getFoodItem().getId()
        );
    }
}
