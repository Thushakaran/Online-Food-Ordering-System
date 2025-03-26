package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);

    OrderItemDto getOrderItemById(Long id);

    List<OrderItemDto> getAllOrderItems();

    OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);

    void deleteOrderItem(Long id);
}
