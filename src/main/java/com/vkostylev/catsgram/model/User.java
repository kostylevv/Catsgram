package com.vkostylev.catsgram.model;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    Long id;
    String username;
    String email;
    String password;
    Instant registrationDate;
}
