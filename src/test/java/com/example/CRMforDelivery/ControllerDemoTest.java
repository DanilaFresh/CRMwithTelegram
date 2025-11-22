package com.example.CRMforDelivery;

import com.example.CRMforDelivery.controller.CourierController;
import com.example.CRMforDelivery.controller.CustomerController;
import com.example.CRMforDelivery.controller.OrderController;
import com.example.CRMforDelivery.service.interfaces.CourierService;
import com.example.CRMforDelivery.service.interfaces.CustomerService;
import com.example.CRMforDelivery.service.interfaces.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest
class ControllerDemoTest {

    @MockitoBean
    private CourierService courierService;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private CourierController courierController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private CustomerController customerController;

    @Test
    void test(){
        Assertions.assertEquals(1,1);
    }
}
