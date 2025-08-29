package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.dto.CustomerDto;
import com.example.CRMforDelivery.entity.dto.mapper.CustomerDtoMapper;
import com.example.CRMforDelivery.repository.CustomerRepository;
import com.example.CRMforDelivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceBaseImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CustomerDtoMapper customerDtoMapper;

    public CustomerServiceBaseImpl(CustomerRepository customerRepository, OrderRepository orderRepository, CustomerDtoMapper customerDtoMapper) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.customerDtoMapper = customerDtoMapper;
    }

    public long addCustomer(CustomerDto customerDto) {
        Customer customer = customerDtoMapper.toEntity(customerDto);
        return customerRepository.save(customer).getId();
    }

    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        CustomerDto customerDto;
        customerDto = optionalCustomer.map(customerDtoMapper::toDto).orElse(null);
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
    public boolean updateCustomer(Long id, CustomerDto customerDto) {
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
