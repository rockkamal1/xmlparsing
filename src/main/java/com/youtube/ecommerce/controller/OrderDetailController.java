package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.entity.OrderInput;
import com.youtube.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderInput orderInput){
        System.out.println("Hello");
        orderDetailService.placeOrder(orderInput);

    }

}
