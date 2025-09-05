package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.CourierDto;

public interface CourierService {
    long addCourier (CourierDto courierDto);
    CourierDto getCourierById(Long id);
    boolean deleteCourierById(Long id);
    boolean updateCourier(Long id , CourierDto courierDto);
}
