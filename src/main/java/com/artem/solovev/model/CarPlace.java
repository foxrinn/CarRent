package com.artem.solovev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "car_places")
public class CarPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(nullable = false)
    private String country;
    @NonNull
    @Column(nullable = false)
    private String city;
    @NonNull
    @Column(nullable = false, unique = true)
    private String address;
    @NonNull
    @Column(nullable = false, unique = true)
    private String phone;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carPlace")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Car> listCars;
}
