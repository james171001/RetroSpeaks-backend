package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.mapper.PostMapper;
import com.champ.retrospeaks.model.Post;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.PostRepository;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.PostService;

import com.champ.retrospeaks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.champ.retrospeaks.mapper.PostMapper.toPost;
import static com.champ.retrospeaks.mapper.PostMapper.toPostDto;
import static com.fasterxml.jackson.core.io.NumberInput.parseInt;

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
        postRepository.save(toPost(postDto));
    }

    @Override
    public List<PostDto> getAllPostByGroupId(int groupId) {
        List<Post> posts = postRepository.findAllByGroupId(groupId);
        return posts.stream().map(PostMapper::toPostDto).collect(Collectors.toList());
    }


}
