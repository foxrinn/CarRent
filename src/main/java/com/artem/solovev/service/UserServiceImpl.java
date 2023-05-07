package com.artem.solovev.service;

import com.artem.solovev.dto.UserDto;
import com.artem.solovev.model.Role;
import com.artem.solovev.model.User;
import com.artem.solovev.repository.RoleRepository;
import com.artem.solovev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFio(userDto.getFio());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        //добавляем роль админа
        String roleName1 = "ROLE_ADMIN";
        //String roleName2 = "ROLE_USER";
        Role role = roleRepository.findByName(roleName1);
        //Role role2 = roleRepository.findByName(roleName2);
        //добавляем роль обычного пользователя, если какая-то роль не нужна будущему пользователю, то
        //можно просто закомментировать строки, запустить приложение и создать пользователя с нужной ролью
        if(role == null){
            role = checkRoleExist(roleName1);
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String fio = user.getFio();
        userDto.setFio(fio);
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        return userDto;
    }

    private Role checkRoleExist(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this user");
        }
    }

}
