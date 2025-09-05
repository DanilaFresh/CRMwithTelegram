package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.Order;
import com.example.CRMforDelivery.entity.dto.OrderDto;
import com.example.CRMforDelivery.entity.dto.mapper.OrderDtoMapper;
import com.example.CRMforDelivery.repository.CustomerRepository;
import com.example.CRMforDelivery.repository.OrderRepository;
import com.example.CRMforDelivery.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderServiceBaseImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderDtoMapper orderDtoMapper;
    @Autowired
    private final CustomerRepository customerRepository;
    public OrderServiceBaseImpl(OrderRepository orderRepository, OrderDtoMapper orderDtoMapper, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderDtoMapper = orderDtoMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public long addOrder(OrderDto orderDto) {
        Optional<Customer> customerOptional=customerRepository.findById(orderDto.getCustomerId());
        if(customerOptional.isEmpty()){
            return -1;
        }
        Order order=orderDtoMapper.toEntity(orderDto,customerOptional.get());
        return  orderRepository.save(order).getId();
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Optional<Order> orderOptional=orderRepository.findById(id);
        return orderOptional.map(orderDtoMapper::toDto).orElse(null);
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
    public boolean updateOrder(Long id, OrderDto orderDto) {
        Optional<Order> orderOptional=orderRepository.findById(id);
        if(orderOptional.isEmpty()){
            return false;
        }
        Optional<Customer> customerOptional=customerRepository.findById(orderDto.getCustomerId());
        if (customerOptional.isEmpty()){
            return false;
        }
        Order orderUpdated=orderDtoMapper.toEntity(orderDto,customerOptional.get());
        orderRepository.save(orderUpdated);
        return true;
    }
}
