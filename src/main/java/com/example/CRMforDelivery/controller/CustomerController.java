package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.dto.CustomerDto;

import com.example.CRMforDelivery.service.interfaces.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        HttpStatus status;
        if (customerDto == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        return ResponseEntity
                .status(status)
                .body(customerDto);
    }

    @PostMapping()
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDto customerDto){
        long id = customerService.addCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/customer/" + id)
                .body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDto customerDto,
                                            @PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (customerService.updateCustomer(id,customerDto)) {
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity
                .status(status)
                .body(null);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (customerService.deleteCustomerById(id)) {
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity
                .status(status)
                .body(null);
    }



}
