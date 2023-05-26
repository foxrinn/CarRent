package com.artem.solovev.service;

import com.artem.solovev.model.Car;
import com.artem.solovev.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Car> searchByCategory(String category) {
        return this.carRepository.searchByCategory(category);
    }

    @Override
    public List<Car> searchByBrand(String brand) {
        return this.carRepository.searchByBrand(brand);
    }

    @Override
    public List<Car> searchByModel(String model) {
        return this.carRepository.searchByModel(model);
    }

    @Override
    public List<Car> searchByYear(int year) {
        return this.carRepository.searchByYear(year);
    }

    @Override
    public List<Car> searchByAvailable(boolean available) {
        return this.carRepository.searchByAvailable(available);
    }

    @Override
    public Car get(long id) {
        return this.carRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Car is not exists!"));
    }
}
