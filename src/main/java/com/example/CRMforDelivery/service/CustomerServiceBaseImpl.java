package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.config.properties.CustomersApiProperties;
import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.dto.CustomerRequestDto;
import com.example.CRMforDelivery.entity.dto.CustomerResponseDto;
import com.example.CRMforDelivery.entity.dto.mapper.CustomerDtoMapper;
import com.example.CRMforDelivery.exceptions.NoSuchCustomerException;
import com.example.CRMforDelivery.repository.CustomerRepository;
import com.example.CRMforDelivery.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceBaseImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerDtoMapper customerDtoMapper;

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
