package com.artem.solovev.repository;

import com.artem.solovev.model.CarPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPlaceRepository extends JpaRepository<CarPlace, Long> {
}
