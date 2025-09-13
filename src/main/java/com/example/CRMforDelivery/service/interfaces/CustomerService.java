package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;


public interface CustomerService {
    long addCustomer (CustomerRequestDto customerDto);
    CustomerResponseDto getCustomerById(Long id);
    boolean deleteCustomerById(Long id);
    boolean updateCustomer(Long id , CustomerRequestDto customerDto);
}
