package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;

import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import com.example.CRMforDelivery.service.interfaces.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("${api.customers.base}")
@Tag(name = "CustomerController", description = "Операции для пользователей")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerRequestDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/{id}/allOrders")
    public ResponseEntity<List<OrderResponseDto>> getAllCustomersOrders(@PathVariable Long id){
        return customerService.getAllCustomersOrders(id);
    }

    @GetMapping("/allCustomersUsingJoinFetch")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersUsingJoinFetch(){
        return customerService.getAllCustomersUsingJoinFetch();
    }

    @GetMapping("/allCustomersUsingLeftJoinFetch")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersUsingLeftJoinFetch(){
        return customerService.getAllCustomersUsingLeftJoinFetch();
    }

    @GetMapping("/allCustomersUsingGraphEntity")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersUsingGraphEntity(){
        return customerService.getAllCustomersUsingGraphEntity();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerRequestDto customerDto,
                                            @PathVariable Long id) {
        return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }


}
