package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.OrderRequestDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<?> addOrder(OrderRequestDto orderDto);

    ResponseEntity<OrderResponseDto> getOrderById(Long id);

    ResponseEntity<?> deleteOrderById(Long id);

    ResponseEntity<?> updateOrder(Long id, OrderRequestDto orderDto);

    ResponseEntity<List<OrderResponseDto>> getAllOrders();
}
