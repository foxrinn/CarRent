package com.artem.solovev.service;

import com.artem.solovev.model.Car;
import com.artem.solovev.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{
    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void add(Car car){
        try {
            this.carRepository.save(car);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this car");
        }
    }
}
