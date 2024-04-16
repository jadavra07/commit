package com.blog.controller;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.services.PostServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServices postServices;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        postServices.createPost(postDto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=5
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Post> posts = postServices.getAllPosts(page, size);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postServices.DeletePost(id);
        return new ResponseEntity<>("Post Is Delete", HttpStatus.OK);
    }

    //http://localhost:8080/api/posts?postId=1
    @PutMapping
    public ResponseEntity<PostDto> updatePost(@RequestParam("postId") long postId, @RequestBody PostDto postDto) {
        PostDto dto = postServices.updatePost(postId, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
