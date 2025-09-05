package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.dto.OrderDto;
import com.example.CRMforDelivery.service.interfaces.OrderService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        HttpStatus status;
        if (orderDto == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        return ResponseEntity
                .status(status)
                .body(orderDto);
    }

    @PostMapping()
    public ResponseEntity<String> addOrder(@Valid @RequestBody OrderDto orderDto) {
        long id = orderService.addOrder(orderDto);
        if (id > 0) {
            return ResponseEntity.created(URI.create("/api/order/" + id)).body(null);
        } else
            return ResponseEntity
                    .unprocessableEntity()
                    .body(" \"error\":" +
                            "\"Customer with id="+
                            orderDto.getCustomerId()
                            +" not found\"");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody OrderDto orderDto,
                                         @PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (orderService.updateOrder(id, orderDto)) {
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity
                .status(status)
                .body(null);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (orderService.deleteOrderById(id)) {
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity
                .status(status)
                .body(null);
    }


}
