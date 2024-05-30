package com.vkostylev.catsgram.service;

import com.vkostylev.catsgram.exception.ConditionsNotMetException;
import com.vkostylev.catsgram.exception.DuplicateDataException;
import com.vkostylev.catsgram.exception.NotFoundException;
import com.vkostylev.catsgram.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User create(User newUser) {
        // проверяем выполнение необходимых условий
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
}
