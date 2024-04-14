package com.digitalbooking.demo.security.controller;


import com.digitalbooking.demo.security.model.User;
import com.digitalbooking.demo.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> findById(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }




}
