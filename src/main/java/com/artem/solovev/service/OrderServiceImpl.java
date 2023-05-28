package com.artem.solovev.service;

import com.artem.solovev.model.Car;
import com.artem.solovev.model.Order;
import com.artem.solovev.model.User;
import com.artem.solovev.model.UserDetailsImpl;
import com.artem.solovev.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private CarService carService;
    private UserService userService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void add(Order order, long carId, Authentication authentication) {
        Car car = this.carService.get(carId);
        order.setCar(car);
        order.setLocalDate(LocalDate.now());
        order.setSum(order.getCountDays() * car.getPrice());
        if(authentication != null && authentication.isAuthenticated()){
            long id = ((UserDetailsImpl)authentication.getPrincipal()).getId();
            User user = this.userService.get(id);
            order.setUser(user);
            try {
                this.orderRepository.save(order);
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not add this order");
            }
        }
    }

    @Override
    public List<Order> getByUserId(long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    @Override
    public List<Order> get() {
        return this.orderRepository.findAll();
    }
}
