package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.CustomerDto;


public interface CustomerService {
    long addCustomer (CustomerDto customerDto);
    CustomerDto getCustomerById(Long id);
    boolean deleteCustomerById(Long id);
    boolean updateCustomer(Long id , CustomerDto customerDto);
}
