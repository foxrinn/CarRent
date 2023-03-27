package com.artem.solovev.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(nullable = false)
    private String category;
    @NonNull
    @Column(nullable = false)
    private String brand;
    @NonNull
    @Column(nullable = false)
    private String model;
    @NonNull
    @Column(nullable = false)
    private int year;
    @NonNull
    @Column(nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "car_place_id")
    private CarPlace carPlace;
}
