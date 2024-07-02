package com.vkostylev.catsgram.controller;

import com.vkostylev.catsgram.dto.NewUserRequest;
import com.vkostylev.catsgram.dto.UpdateUserRequest;
import com.vkostylev.catsgram.dto.UserDto;
import com.vkostylev.catsgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody NewUserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") long userId, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(userId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }
}
