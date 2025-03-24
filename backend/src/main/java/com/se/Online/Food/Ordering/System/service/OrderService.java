package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> getOrdersByUserId(Long userId);
}
