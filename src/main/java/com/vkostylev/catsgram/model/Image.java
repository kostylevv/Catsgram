package com.vkostylev.catsgram.model;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Image {
    Long id;
    long postId;
    String originalFileName;
    String filePath;
}
