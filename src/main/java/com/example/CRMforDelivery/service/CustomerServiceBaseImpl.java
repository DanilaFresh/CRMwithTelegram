package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;
import com.example.CRMforDelivery.entity.dto.mapper.CustomerDtoMapper;
import com.example.CRMforDelivery.repository.CustomerRepository;
import com.example.CRMforDelivery.repository.OrderRepository;
import com.example.CRMforDelivery.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceBaseImpl implements CustomerService {


    private final CustomerRepository customerRepository;


    private final OrderRepository orderRepository;

    private final CustomerDtoMapper customerDtoMapper;


    public long addCustomer(CustomerRequestDto customerDto) {
        Customer customer = customerDtoMapper.toEntity(customerDto);
        return customerRepository.save(customer).getId();
    }

    public CustomerResponseDto getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        CustomerResponseDto customerDto;
        customerDto = optionalCustomer.map(customerDtoMapper::toResponseDto).orElse(null);
        return customerDto;

    }

    public boolean deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {

            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean updateCustomer(Long id, CustomerRequestDto customerDto) {
        Customer customerUpdated = customerDtoMapper.toEntity(customerDto);
        Optional<Customer> customerOld = customerRepository.findById(id);
        if (customerOld.isPresent()) {
            customerUpdated.setId(id);
            customerRepository.save(customerUpdated);
            return true;
        } else {
            return false;
        }


    }


}
