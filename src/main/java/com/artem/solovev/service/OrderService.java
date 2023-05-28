package com.artem.solovev.service;

import com.artem.solovev.model.Order;
import com.artem.solovev.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    void add(Order order, long carId, Authentication authentication);

    List<Order> getByUserId(long userId);

    List<Order> get();
}
