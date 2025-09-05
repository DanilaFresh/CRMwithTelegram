package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.OrderDto;

public interface OrderService {
    long addOrder (OrderDto orderDto);
    OrderDto getOrderById(Long id);
    boolean deleteOrderById(Long id);
    boolean updateOrder(Long id , OrderDto orderDto);
}
