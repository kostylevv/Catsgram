package com.vkostylev.catsgram.dal.mappers;

import com.vkostylev.catsgram.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));

        Timestamp registrationDate = resultSet.getTimestamp("registration_date");
        user.setRegistrationDate(registrationDate.toInstant());

        return user;
    }
}