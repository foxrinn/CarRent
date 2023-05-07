package com.artem.solovev.service;

import com.artem.solovev.dto.UserDto;
import com.artem.solovev.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
