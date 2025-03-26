package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();

    OrderDTO updateOrder(Long id, OrderDTO orderDTO); // Update method

    void deleteOrder(Long id);
}
