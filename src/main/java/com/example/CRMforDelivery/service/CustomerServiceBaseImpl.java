package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.config.properties.CustomersApiProperties;
import com.example.CRMforDelivery.config.properties.OrdersApiProperties;
import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import com.example.CRMforDelivery.entity.dto.mapper.CustomerDtoMapper;
import com.example.CRMforDelivery.entity.dto.mapper.OrderDtoMapper;
import com.example.CRMforDelivery.exceptions.NoSuchCustomerException;
import com.example.CRMforDelivery.repository.CustomerRepository;
import com.example.CRMforDelivery.repository.OrderRepository;
import com.example.CRMforDelivery.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceBaseImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerDtoMapper customerDtoMapper;

    private final OrderRepository orderRepository;

    private final OrderDtoMapper orderDtoMapper;

    private final MessageSource messageSource;

    private final CustomersApiProperties customersApiProperties;


    public ResponseEntity<?> addCustomer(CustomerRequestDto customerDto) {
        Customer customer = customerDtoMapper.toEntity(customerDto);
        long id = customerRepository.save(customer).getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, customersApiProperties.getBase() + id)
                .body(null);

    }

    public ResponseEntity<CustomerResponseDto> getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        CustomerResponseDto customerDto;
        customerDto = optionalCustomer
                .map(customerDtoMapper::toResponseDto)
                .orElseThrow(() -> {
                    var message = messageSource
                            .getMessage("customer.not.found", new Object[]{id.toString()}, Locale.getDefault());
                    return new NoSuchCustomerException(message);
                });
        return ResponseEntity
                .ok()
                .body(customerDto);

    }

    @Override
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomersUsingGraphEntity() {
        List<Customer> customers=customerRepository.findAllWithOrdersUsingGraphEntity();
        customers.forEach(customer -> customer.getOrders().size());
        var customerResponseDtoList=customers
                .stream()
                .map(customerDtoMapper::toResponseDto)
                .toList();
        return ResponseEntity
                .ok()
                .body(customerResponseDtoList);
    }

    @Override
    public ResponseEntity<List<CustomerResponseDto>>  getAllCustomersUsingJoinFetch() {
        List<Customer> customers=customerRepository.findAllWithOrdersUsingJoinFetch();
        customers.forEach(customer -> customer.getOrders().size());
        var customerResponseDtoList=customers
                .stream()
                .map(customerDtoMapper::toResponseDto)
                .toList();
        return ResponseEntity
                .ok()
                .body(customerResponseDtoList);
    }

    @Override
    public ResponseEntity<List<CustomerResponseDto>>  getAllCustomersUsingLeftJoinFetch() {
        List<Customer> customers=customerRepository.findAllWithOrdersUsingLeftJoinFetch();
        customers.forEach(customer -> customer.getOrders().size());
        var customerResponseDtoList=customers
                .stream()
                .map(customerDtoMapper::toResponseDto)
                .toList();
        return ResponseEntity
                .ok()
                .body(customerResponseDtoList);
    }


    public ResponseEntity<List<OrderResponseDto>> getAllCustomersOrders(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
       return optionalCustomer.map((customer)->{
//           var responseDtoList=orderRepository.findOrdersByCustomersId(id)
//                   .stream()
//                   .map(orderDtoMapper::toResponseDto)
//                   .toList();
           var responseDtoList=customer.getOrders()
                   .stream()
                   .map(orderDtoMapper::toResponseDto)
                   .toList();
           return ResponseEntity
                   .ok()
                   .body(responseDtoList);
       }).orElseThrow(() -> {
           var message = messageSource
                   .getMessage("customer.not.found", new Object[]{id.toString()}, Locale.getDefault());
           return new NoSuchCustomerException(message);
       });

    }


    public ResponseEntity<?> updateCustomer(Long id, CustomerRequestDto customerDto) {
        Customer customerUpdated = customerDtoMapper.toEntity(customerDto);
        Optional<Customer> customerOld = customerRepository.findById(id);
        customerOld.orElseThrow(() -> {
            var message = messageSource
                    .getMessage("customer.not.found", new Object[]{id.toString()}, Locale.getDefault());
            return new NoSuchCustomerException(message);
        });
        customerUpdated.setId(id);
        customerRepository.save(customerUpdated);
        return ResponseEntity
                .noContent()
                .build();
    }

    public ResponseEntity<?> deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            var message = messageSource
                    .getMessage("customer.not.found", new Object[]{id.toString()}, Locale.getDefault());
            throw new NoSuchCustomerException(message);
        }
        customerRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();

    }


}
