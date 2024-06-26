package com.vkostylev.catsgram.controller;

import com.vkostylev.catsgram.model.User;
import com.vkostylev.catsgram.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/user/{userMail}")
    public User getUser(@PathVariable("userMail") String userMail) {
        return userService.findUserByEmail(userMail);
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