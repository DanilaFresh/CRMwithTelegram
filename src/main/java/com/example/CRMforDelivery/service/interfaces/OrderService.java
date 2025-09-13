package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.OrderRequestDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;

public interface OrderService {
    long addOrder (OrderRequestDto orderDto);
    OrderResponseDto getOrderById(Long id);
    boolean deleteOrderById(Long id);
    boolean updateOrder(Long id , OrderRequestDto orderDto);
}
