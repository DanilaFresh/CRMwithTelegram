package com.example.CRMforDelivery.entity.dto.mapper;

import com.example.CRMforDelivery.entity.Courier;
import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.Order;
import com.example.CRMforDelivery.entity.dto.CourierDto;
import com.example.CRMforDelivery.entity.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {
    public Order toEntity(OrderDto orderDto, Customer customer) {
        return new Order(
                orderDto.getStatus(),
                customer,
                orderDto.getAddress(),
                orderDto.getCargoDescription(),
                orderDto.getDeliveryWishes());

    }

    public OrderDto toDto(Order order){
        return new OrderDto(
                order.getCustomer().getId(),
                order.getStatus(),
                order.getAddress(),
                order.getCargoDescription(),
                order.getDeliveryWishes()
        );
    }
}
