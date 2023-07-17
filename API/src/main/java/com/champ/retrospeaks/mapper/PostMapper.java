package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Comment.CommentDto;
import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.Post;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PostMapper {
    static UserRepository userRepository;
    static UserService userService;
    public static Post toPost(Optional<PostDto> postDto){
        return Post.builder()
                .id(postDto.get().getId())
                .userID(postDto.get().getUserID())
                .username(postDto.get().getUsername())
                .postType(postDto.get().getPostType())
                .title(postDto.get().getTitle())
                .content(postDto.get().getContent())
                .postDate(postDto.get().getPostDate())
                .category(postDto.get().getCategory())
                .groupId(postDto.get().getGroupId())
                .agreeCount(postDto.get().getAgreeCount())
                .disagreeCount(postDto.get().getDisagreeCount())
                .build();
    }

    public static PostDto toPostDto(Optional<Post> post) {

        return PostDto.builder()
                .id(post.get().getId())
                .userID(post.get().getUserID())
                .username(post.get().getUsername())
                .postType(post.get().getPostType())
                .title(post.get().getTitle())
                .content(post.get().getContent())
                .postDate(post.get().getPostDate())
                .category(post.get().getCategory())
                .groupId(post.get().getGroupId())
                .agreeCount(post.get().getAgreeCount())
                .disagreeCount(post.get().getDisagreeCount())
                .build();
    }

}
