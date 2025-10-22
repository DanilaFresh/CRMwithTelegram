package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.config.properties.OrdersApiProperties;
import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.Order;
import com.example.CRMforDelivery.entity.dto.OrderRequestDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import com.example.CRMforDelivery.entity.dto.mapper.OrderDtoMapper;
import com.example.CRMforDelivery.exceptions.NoSuchCustomerException;
import com.example.CRMforDelivery.exceptions.NoSuchOrderException;
import com.example.CRMforDelivery.repository.CustomerRepository;
import com.example.CRMforDelivery.repository.OrderRepository;
import com.example.CRMforDelivery.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
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
public class OrderServiceBaseImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDtoMapper orderDtoMapper;

    private final MessageSource messageSource;

    private final CustomerRepository customerRepository;

    private final OrdersApiProperties ordersApiProperties;


    @Override
    public ResponseEntity<?> addOrder(OrderRequestDto orderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(orderDto.customerId());
        customerOptional.orElseThrow(() -> {
            var message = messageSource
                    .getMessage("customer.not.found",
                            new Object[]{orderDto.customerId()},
                            Locale.getDefault());
            return new NoSuchCustomerException(message);
        });
        Order order = orderDtoMapper.toEntity(orderDto, customerOptional.get());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, ordersApiProperties.getBase() + order.getId())
                .body(null);

    }

    @Override
    public ResponseEntity<OrderResponseDto> getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        OrderResponseDto orderDto = orderOptional
                .map(orderDtoMapper::toResponseDto)
                .orElseThrow(() -> {
                    var message = messageSource
                            .getMessage("order.not.found",
                                    new Object[]{id.toString()},
                                    Locale.getDefault());
                    return new NoSuchOrderException(message);
                });
        return ResponseEntity
                .ok()
                .body(orderDto);
    }

    @Override
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity
                .ok()
                .body(orderRepository.findAll()
                        .stream()
                        .map(orderDtoMapper::toResponseDto)
                        .toList());

    }

    @Override
    public ResponseEntity<?> updateOrder(Long id, OrderRequestDto orderDto) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        orderOptional.orElseThrow(() -> {
            var message = messageSource
                    .getMessage("order.not.found",
                            new Object[]{id.toString()},
                            Locale.getDefault());
            return new NoSuchOrderException(message);
        });

        Optional<Customer> customerOptional = customerRepository.findById(orderDto.customerId());
        customerOptional.orElseThrow(() -> {
            var message = messageSource
                    .getMessage("customer.not.found",
                            new Object[]{orderDto.customerId()},
                            Locale.getDefault());
            return new NoSuchCustomerException(message);
        });
        Order orderUpdated = orderDtoMapper.toEntity(orderDto, customerOptional.get());
        orderUpdated.setId(id);
        orderRepository.save(orderUpdated);
        return ResponseEntity
                .noContent()
                .build();
    }


    @Override
    public ResponseEntity<?> deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            var message = messageSource
                    .getMessage("order.not.found",
                            new Object[]{id.toString()},
                            Locale.getDefault());
            throw new NoSuchOrderException(message);
        }
        orderRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();

    }


}
