package com.artem.solovev.controllers;

import com.artem.solovev.dto.ResponseResult;
import com.artem.solovev.model.Order;
import com.artem.solovev.model.User;
import com.artem.solovev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/{carId}")
    public ResponseEntity<ResponseResult<Order>> add(@PathVariable long carId, @RequestBody Order order,
                                                     Authentication authentication){
        try {
            this.orderService.add(order, carId, authentication);
            return new ResponseEntity<>(new ResponseResult<>(order, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<ResponseResult<List<Order>>> get(@PathVariable long userId){
        List<Order> orders = this.orderService.getByUserId(userId);
        return new ResponseEntity<>(new ResponseResult<>(orders, null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Order>>> get(){
        try {
            List<Order> orders = this.orderService.get();
            return new ResponseEntity<>(new ResponseResult<>(orders, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
