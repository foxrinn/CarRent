package com.artem.solovev.service;

import com.artem.solovev.model.CarPlace;
import com.artem.solovev.model.User;

import java.util.List;

public interface CarPlaceService {
    void add(CarPlace carPlace);

    CarPlace get(long id);

    List<CarPlace> get();
}
