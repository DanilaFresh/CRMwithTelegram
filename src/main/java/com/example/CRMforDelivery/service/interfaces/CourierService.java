package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import org.springframework.http.ResponseEntity;

public interface CourierService {
    ResponseEntity<?> addCourier (CourierRequestDto courierDto);
    ResponseEntity<CourierResponseDto> getCourierById(Long id);
    ResponseEntity<?> deleteCourierById(Long id);
    ResponseEntity<?> updateCourier(Long id , CourierRequestDto courierDto);
    ResponseEntity<Boolean> findByTgUserName(String username);
}
