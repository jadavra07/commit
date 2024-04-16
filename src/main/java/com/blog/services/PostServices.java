package com.blog.services;

import com.blog.entity.Post;
import com.blog.payload.PostDto;

import java.util.List;

public interface PostServices {
    public PostDto createPost(PostDto postDto);

    void DeletePost(long id);
   

    PostDto updatePost(long postId, PostDto postDto);

    List<Post> getAllPosts(int page, int size);
}
