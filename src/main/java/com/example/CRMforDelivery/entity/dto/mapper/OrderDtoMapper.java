package com.example.CRMforDelivery.entity.dto.mapper;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.Order;
import com.example.CRMforDelivery.entity.dto.OrderRequestDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {
    public Order toEntity(OrderResponseDto orderDto, Customer customer) {
        return new Order(
                orderDto.status(),
                customer,
                orderDto.address(),
                orderDto.cargoDescription(),
                orderDto.deliveryWishes());

    }
    public Order toEntity(OrderRequestDto orderDto, Customer customer) {
        return new Order(
                orderDto.status(),
                customer,
                orderDto.address(),
                orderDto.cargoDescription(),
                orderDto.deliveryWishes());

    }

    public OrderResponseDto toResponseDto(Order order){
        return new OrderResponseDto(
                order.getCustomer().getId(),
                order.getStatus(),
                order.getAddress(),
                order.getCargoDescription(),
                order.getDeliveryWishes()
        );
    }
    public OrderRequestDto toRequestDto(Order order){
        return new OrderRequestDto(
                order.getCustomer().getId(),
                order.getStatus(),
                order.getAddress(),
                order.getCargoDescription(),
                order.getDeliveryWishes()
        );
    }
}
