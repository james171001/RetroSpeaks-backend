package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.Post;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public class PostMapper {
    static UserRepository userRepository;
    static UserService userService;
    public static Post toPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .userID(postDto.getUserID())
                .username(postDto.getUsername())
                .postType(postDto.getPostType())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .postDate(postDto.getPostDate())
                .category(postDto.getCategory())
                .groupId(postDto.getGroupId())
                .build();
    }

    public static PostDto toPostDto(Post post){

        return PostDto.builder()
                .username(post.getUsername())
                .postType(post.getPostType())
                .title(post.getTitle())
                .content(post.getContent())
                .postDate(post.getPostDate())
                .category(post.getCategory())
                .groupId(post.getGroupId())
                .build();
    }
}
