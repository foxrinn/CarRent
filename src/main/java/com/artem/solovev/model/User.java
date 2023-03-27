package com.artem.solovev.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(nullable = false)
    private String fio;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    @NonNull
    private String phone;

    @Column(nullable = false)
    @NonNull
    private String passport;
}
