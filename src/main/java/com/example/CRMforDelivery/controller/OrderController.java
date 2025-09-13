package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.dto.OrderRequestDto;
import com.example.CRMforDelivery.entity.dto.OrderResponseDto;
import com.example.CRMforDelivery.service.interfaces.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long id) {
        OrderResponseDto orderDto = orderService.getOrderById(id);
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
    public ResponseEntity<String> addOrder(@Valid @RequestBody OrderRequestDto orderDto) {
        long id = orderService.addOrder(orderDto);
        if (id > 0) {
            return ResponseEntity.created(URI.create("/api/order/" + id)).body(null);
        } else
            return ResponseEntity
                    .unprocessableEntity()
                    .body(" \"error\":" +
                            "\"Customer with id="+
                            orderDto.customerId()
                            +" not found\"");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody OrderRequestDto orderDto,
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
