package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;
import org.springframework.http.ResponseEntity;


public interface CustomerService {
    ResponseEntity<?> addCustomer (CustomerRequestDto customerDto);
    ResponseEntity<CustomerResponseDto> getCustomerById(Long id);
    ResponseEntity<?> deleteCustomerById(Long id);
    ResponseEntity<?> updateCustomer(Long id , CustomerRequestDto customerDto);
}
