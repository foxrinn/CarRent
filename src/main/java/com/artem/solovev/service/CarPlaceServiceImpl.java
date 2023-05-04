package com.artem.solovev.service;

import com.artem.solovev.model.CarPlace;
import com.artem.solovev.repository.CarPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarPlaceServiceImpl implements CarPlaceService {
    private CarPlaceRepository carPlaceRepository;

    @Autowired
    public void setCarPlaceRepository(CarPlaceRepository carPlaceRepository) {
        this.carPlaceRepository = carPlaceRepository;
    }

    @Override
    public void add(CarPlace carPlace) {
        try {
            this.carPlaceRepository.save(carPlace);
        } catch (Exception e) {
            throw new IllegalArgumentException("This car place has already added!");
        }
    }

    @Override
    public CarPlace get(long id) {
        return this.carPlaceRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Car place does not exists!"));
    }
}
