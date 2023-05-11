package com.artem.solovev.controllers;

import com.artem.solovev.dto.ResponseResult;
import com.artem.solovev.model.User;
import com.artem.solovev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<User>> add(@RequestBody User user){
        try {
            this.userService.add(user);
            return new ResponseEntity<>(new ResponseResult<>(user, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<User>>> get(){
        try {
            List<User> users = this.userService.get();
            return new ResponseEntity<>(new ResponseResult<>(users, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
