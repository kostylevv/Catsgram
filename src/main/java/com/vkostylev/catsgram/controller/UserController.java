package com.vkostylev.catsgram.controller;

import com.vkostylev.catsgram.model.User;
import com.vkostylev.catsgram.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User newUser) {
        return userService.create(newUser);
    }

    @PutMapping
    public User update(@RequestBody User updatedUser) {
        return userService.update(updatedUser);
    }
}