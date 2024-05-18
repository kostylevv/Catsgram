package com.vkostylev.catsgram.model;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Post {
    Long id;
    long authorId;
    String description;
    Instant postDate;
}
