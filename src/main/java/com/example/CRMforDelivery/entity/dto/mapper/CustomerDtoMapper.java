package com.example.CRMforDelivery.entity.dto.mapper;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {

    public Customer toEntity(CustomerResponseDto customerDto){
        Customer customer=new Customer();
        customer.setName(customerDto.name());
        customer.setSurname(customerDto.surname());
        customer.setLast_name(customerDto.last_name());
        customer.setPhone_number(customerDto.phone_number());
        return customer;

    }
    public Customer toEntity(CustomerRequestDto customerDto){
        Customer customer=new Customer();
        customer.setName(customerDto.name());
        customer.setSurname(customerDto.surname());
        customer.setLast_name(customerDto.last_name());
        customer.setPhone_number(customerDto.phone_number());
        return customer;

    }

    public CustomerRequestDto toRequestDto(Customer customer){
        return new CustomerRequestDto(
                customer.getName(),
                customer.getSurname(),
                customer.getLast_name(),
                customer.getPhone_number());
    }
    public CustomerResponseDto toResponseDto(Customer customer){
        return new CustomerResponseDto(
                customer.getName(),
                customer.getSurname(),
                customer.getLast_name(),
                customer.getPhone_number());
    }
}
