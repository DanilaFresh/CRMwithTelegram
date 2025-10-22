package com.example.CRMforDelivery.service.interfaces;

import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CustomerService {
    ResponseEntity<?> addCustomer(CustomerRequestDto customerDto);

    ResponseEntity<CustomerResponseDto> getCustomerById(Long id);

    ResponseEntity<List<CustomerResponseDto>> getAllCustomersUsingGraphEntity();

    ResponseEntity<List<CustomerResponseDto>> getAllCustomersUsingJoinFetch();

    ResponseEntity<List<CustomerResponseDto>> getAllCustomersUsingLeftJoinFetch();

    ResponseEntity<?> deleteCustomerById(Long id);

    ResponseEntity<?> updateCustomer(Long id, CustomerRequestDto customerDto);

    ResponseEntity<List<OrderResponseDto>> getAllCustomersOrders(Long id);


}
