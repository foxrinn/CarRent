package com.artem.solovev.controllers;

import com.artem.solovev.dto.ResponseResult;
import com.artem.solovev.model.Car;
import com.artem.solovev.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    //TODO когда будет готов модуль работы с CarPlaceService нужно будет сюда подать id магазина чтобы добавить его к автомобилю
    @PostMapping
    public ResponseEntity<ResponseResult<Car>> add(@RequestBody Car car){
        try {
            this.carService.add(car);
            return new ResponseEntity<>(new ResponseResult<>(car, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{category}")
    public List<Car> getByCategory(@PathVariable String category){
        return this.carService.searchByCategory(category);
    }

    @GetMapping("/brand/{brand}")
    public List<Car> getByBrand(@PathVariable String brand){
        return this.carService.searchByBrand(brand);
    }

    @GetMapping("/model/{model}")
    public List<Car> getByModel(@PathVariable String model){
        return this.carService.searchByModel(model);
    }

    @GetMapping("/year/{year}")
    public List<Car> getByYear(@PathVariable int year){
        return this.carService.searchByYear(year);
    }

    @GetMapping("/available/{available}")
    public List<Car> getByAvailable(@PathVariable boolean available){
        return this.carService.searchByAvailable(available);
    }
}
