package com.vkostylev.catsgram.service;

import com.vkostylev.catsgram.dal.UserRepository;
import com.vkostylev.catsgram.dto.NewUserRequest;
import com.vkostylev.catsgram.dto.UpdateUserRequest;
import com.vkostylev.catsgram.dto.UserDto;
import com.vkostylev.catsgram.exception.ConditionsNotMetException;
import com.vkostylev.catsgram.exception.DuplicatedDataException;
import com.vkostylev.catsgram.exception.NotFoundException;
import com.vkostylev.catsgram.mapper.UserMapper;
import com.vkostylev.catsgram.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(NewUserRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }

        Optional<User> alreadyExistUser = userRepository.findByEmail(request.getEmail());
        if (alreadyExistUser.isPresent()) {
            throw new DuplicatedDataException("Данный имейл уже используется");
        }

        User user = UserMapper.mapToUser(request);

        user = userRepository.save(user);

        return UserMapper.mapToUserDto(user);
    }

    public UserDto getUserById(long userId) {
        return userRepository.findById(userId)
                .map(UserMapper::mapToUserDto)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден с ID: " + userId));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(long userId, UpdateUserRequest request) {
        User updatedUser = userRepository.findById(userId)
                .map(user -> UserMapper.updateUserFields(user, request))
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        updatedUser = userRepository.update(updatedUser);
        return UserMapper.mapToUserDto(updatedUser);
    }
}

    /*
    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        return users.get(email);
    }

    public User create(User newUser) {
        if (newUser.getEmail() == null || newUser.getEmail().isBlank()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }
        if (findAll().stream().anyMatch(u -> u.getEmail().equals(newUser.getEmail()))) {
            throw new DuplicateDataException("Этот имейл уже используется");
        }
        newUser.setId(getNextId());
        newUser.setRegistrationDate(Instant.now());
        users.put(newUser.getId(), newUser);
        return newUser;
    }

    public User update(User updatedUser) {
        // проверяем необходимые условия
        if (updatedUser.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (users.containsKey(updatedUser.getId())) {
            User oldUser = users.get(updatedUser.getId());
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(oldUser.getEmail())) {
                if (findAll().stream().anyMatch(u -> u.getEmail().equals(updatedUser.getEmail()))) {
                    throw new DuplicateDataException("Этот имейл уже используется");
                }
                oldUser.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getUsername() != null) {
                oldUser.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getPassword() != null) {
                oldUser.setPassword(updatedUser.getPassword());
            }

            return oldUser;
        }
        throw new NotFoundException("Пользователь с id = " + updatedUser.getId() + " не найден");
    }

    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

     */

