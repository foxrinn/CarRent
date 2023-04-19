package com.artem.solovev.repository;

import com.artem.solovev.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> searchByCategory(String category);
    List<Car> searchByBrand(String brand);
    List<Car> searchByModel(String model);
    List<Car> searchByYear(int year);
    List<Car> searchByAvailable(boolean available);
}
