package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;

public interface CourierService {
    long addCourier (CourierRequestDto courierDto);
    CourierResponseDto getCourierById(Long id);
    boolean deleteCourierById(Long id);
    boolean updateCourier(Long id , CourierRequestDto courierDto);
    boolean findByTgUserName(String username);
}
