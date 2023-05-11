package com.artem.solovev.service;

import com.artem.solovev.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> get();
}
