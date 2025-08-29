package com.example.CRMforDelivery.entity.dto.mapper;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {

    public Customer toEntity(CustomerDto customerDto){
        Customer customer=new Customer();
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setLast_name(customerDto.getLast_name());
        customer.setPhone_number(customerDto.getPhone_number());
        return customer;

    }

    public CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getName(),
                customer.getSurname(),
                customer.getLast_name(),
                customer.getPhone_number());
    }
}
