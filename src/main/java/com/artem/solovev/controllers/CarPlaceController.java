package com.artem.solovev.controllers;

import com.artem.solovev.dto.ResponseResult;
import com.artem.solovev.model.CarPlace;
import com.artem.solovev.model.User;
import com.artem.solovev.service.CarPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carplace")
public class CarPlaceController {
    private CarPlaceService carPlaceService;

    @Autowired
    public void setCarPlaceService(CarPlaceService carPlaceService) {
        this.carPlaceService = carPlaceService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<CarPlace>> add(@RequestBody CarPlace carPlace) {
        try {
            this.carPlaceService.add(carPlace);
            return new ResponseEntity<>(new ResponseResult<>(carPlace, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<CarPlace>>> get(){
        try {
            List<CarPlace> carPlaces = this.carPlaceService.get();
            return new ResponseEntity<>(new ResponseResult<>(carPlaces, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
