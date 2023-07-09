package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.Post;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.PostRepository;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.PostService;
import com.champ.retrospeaks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.champ.retrospeaks.mapper.PostMapper.toPost;
import static com.champ.retrospeaks.mapper.PostMapper.toPostDto;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    UserRepository userRepository;
    UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void savePost(PostDto postDto) {
        Optional<User> user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        postDto.setUserID(user.get().getId());
        postDto.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        postDto.setCurrentPostDate();
        postRepository.save(toPost(Optional.of(postDto)));
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> toPostDto(Optional.ofNullable(post))).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return toPostDto(post);
    }

    @Override
    public List<PostDto> getAllPostByGroupId(int groupId) {
        List<Post> posts = postRepository.findAllByGroupId(groupId);
        return posts.stream().map(post -> toPostDto(Optional.ofNullable(post))).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByUserID(Long userID) {
        List<Post> posts = postRepository.findAllPostByUserID(userID);
        return posts.stream().map(post -> toPostDto(Optional.ofNullable(post))).collect(Collectors.toList());
    }

    @Override
    public Optional<PostDto> agreePost(String id) {
        Optional<Post> existingPost = postRepository.findById(id);

        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            int agreeCount = post.getAgreeCount() + 1;
            post.setAgreeCount(agreeCount);
            postRepository.save(post);
            return Optional.of(toPostDto(Optional.of(post)));
        }

        return Optional.empty();
    }

    @Override
    public Optional<PostDto> disAgreePost(String id) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            int disagreeCount = post.getDisagreeCount()+1;
            post.setDisagreeCount(disagreeCount);
            postRepository.save(post);
            return Optional.of(toPostDto(Optional.of(post)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PostDto> updatePost(PostDto postDto) {
        Optional<Post> existingPost = postRepository.findById(postDto.getId());

        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            // Update other fields as needed

            Post updatedPost = postRepository.save(post);
            return Optional.of(toPostDto(Optional.of(updatedPost)));
        }

        return Optional.empty();
    }

    @Override
    public void deletePostById(String id) {
        postRepository.deleteById(id);
    }

}
