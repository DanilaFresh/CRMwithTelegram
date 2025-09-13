package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.Order;
import com.example.CRMforDelivery.entity.dto.OrderRequestDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import com.example.CRMforDelivery.entity.dto.mapper.OrderDtoMapper;
import com.example.CRMforDelivery.repository.CustomerRepository;
import com.example.CRMforDelivery.repository.OrderRepository;
import com.example.CRMforDelivery.service.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderServiceBaseImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDtoMapper orderDtoMapper;

    private final CustomerRepository customerRepository;
    public OrderServiceBaseImpl(OrderRepository orderRepository, OrderDtoMapper orderDtoMapper, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderDtoMapper = orderDtoMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public long addOrder(OrderRequestDto orderDto) {
        Optional<Customer> customerOptional=customerRepository.findById(orderDto.customerId());
        if(customerOptional.isEmpty()){
            return -1;
        }
        Order order=orderDtoMapper.toEntity(orderDto,customerOptional.get());
        return  orderRepository.save(order).getId();
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Optional<Order> orderOptional=orderRepository.findById(id);
        return orderOptional.map(orderDtoMapper::toResponseDto).orElse(null);
    }

    @Override
    public boolean deleteOrderById(Long id) {
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrder(Long id, OrderRequestDto orderDto) {
        Optional<Order> orderOptional=orderRepository.findById(id);
        if(orderOptional.isEmpty()){
            return false;
        }
        Optional<Customer> customerOptional=customerRepository.findById(orderDto.customerId());
        if (customerOptional.isEmpty()){
            return false;
        }
        Order orderUpdated=orderDtoMapper.toEntity(orderDto,customerOptional.get());
        orderRepository.save(orderUpdated);
        return true;
    }
}
