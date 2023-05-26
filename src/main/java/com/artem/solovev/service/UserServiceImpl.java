package com.artem.solovev.service;

import com.artem.solovev.model.User;
import com.artem.solovev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User user) {
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this user");
        }
    }

    @Override
    public List<User> get() {
        return this.userRepository.findAll();
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User is not exists!"));
    }


}
