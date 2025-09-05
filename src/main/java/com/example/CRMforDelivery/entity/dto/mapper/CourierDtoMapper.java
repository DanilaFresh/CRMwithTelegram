package com.example.CRMforDelivery.entity.dto.mapper;

import com.example.CRMforDelivery.entity.Courier;
import com.example.CRMforDelivery.entity.dto.CourierDto;
import com.example.CRMforDelivery.entity.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CourierDtoMapper {
    public Courier toEntity(CourierDto courierDto) {
        Courier courier = new Courier();
        courier.setName(courierDto.getName());
        courier.setTgUserName(courierDto.getTgUserName());
        courier.setSurname(courierDto.getSurname());
        courier.setLast_name(courierDto.getLast_name());
        courier.setPhone_number(courierDto.getPhone_number());
        return courier;

    }

    public CourierDto toDto(Courier courier) {
          return new CourierDto(
                courier.getName(),
                courier.getTgUserName(),
                courier.getSurname(),
                courier.getLast_name(),
                courier.getPhone_number());
    }
}
