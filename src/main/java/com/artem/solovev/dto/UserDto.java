package com.artem.solovev.dto;

import jakarta.validation.constraints.*;
import lombok.*;

//import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty
    private String fio;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
