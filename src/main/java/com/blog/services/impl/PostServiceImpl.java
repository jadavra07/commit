package com.blog.services.impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;
import com.blog.services.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostServices {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = MapToEntity(postDto);
        Post saved = postRepository.save(post);
        return MapTODto(saved);

    }

    @Override
    public void DeletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not fount with id:" + id)
        );
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.getContent();
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post not found with id:"+ postId)
        );
        return postDto;
    }


    Post MapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    PostDto MapTODto(Post post) {
        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;

    }
}
