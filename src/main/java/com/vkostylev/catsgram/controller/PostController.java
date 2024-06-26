package com.vkostylev.catsgram.controller;

import com.vkostylev.catsgram.exception.ConditionsNotMetException;
import com.vkostylev.catsgram.model.Post;
import com.vkostylev.catsgram.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/post/{postId}")
    public Post findPost(@PathVariable("postId") Integer postId) {
        return postService.findById(postId).orElseThrow(() -> new ConditionsNotMetException("Указанный пост не найден"));
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
       return postService.create(post);
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }
}