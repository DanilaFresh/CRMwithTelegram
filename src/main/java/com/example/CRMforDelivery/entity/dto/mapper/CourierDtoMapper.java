package com.example.CRMforDelivery.entity.dto.mapper;

import com.example.CRMforDelivery.entity.Courier;
import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CourierDtoMapper {
    public Courier toEntity(CourierResponseDto courierDto) {
        Courier courier = new Courier();
        courier.setName(courierDto.getName());
        courier.setTgUserName(courierDto.getTgUserName());
        courier.setSurname(courierDto.getSurname());
        courier.setLast_name(courierDto.getLast_name());
        courier.setPhone_number(courierDto.getPhone_number());
        return courier;

    }
    public Courier toEntity(CourierRequestDto courierDto) {
        Courier courier = new Courier();
        courier.setName(courierDto.getName());
        courier.setTgUserName(courierDto.getTgUserName());
        courier.setSurname(courierDto.getSurname());
        courier.setLast_name(courierDto.getLast_name());
        courier.setPhone_number(courierDto.getPhone_number());
        return courier;

    }

    public CourierRequestDto toRequestDto(Courier courier) {
          return new CourierRequestDto(
                courier.getName(),
                courier.getTgUserName(),
                courier.getSurname(),
                courier.getLast_name(),
                courier.getPhone_number());
    }
    public CourierResponseDto toResponseDto(Courier courier) {
        return new CourierResponseDto(
                courier.getName(),
                courier.getTgUserName(),
                courier.getSurname(),
                courier.getLast_name(),
                courier.getPhone_number());
    }
}
