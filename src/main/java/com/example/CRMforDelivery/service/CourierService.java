package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.dto.CourierDto;
import com.example.CRMforDelivery.entity.dto.CustomerDto;

public interface CourierService {
    long addCourier (CourierDto courierDto);
    CourierDto getCourierById(Long id);
    boolean deleteCourierById(Long id);
    boolean updateCourier(Long id , CourierDto courierDto);
}
