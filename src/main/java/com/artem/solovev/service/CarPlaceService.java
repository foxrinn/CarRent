package com.artem.solovev.service;

import com.artem.solovev.model.CarPlace;

public interface CarPlaceService {
    void add(CarPlace carPlace);

    CarPlace get(long id);
}
