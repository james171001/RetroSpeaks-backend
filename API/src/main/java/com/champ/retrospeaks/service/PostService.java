package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Post.PostDto;

import java.util.List;
import java.util.Optional;


public interface PostService {
    void savePost(PostDto postDto);

    public List<PostDto> getAllPosts();

    public PostDto getPostById(String id);

    public List<PostDto> getAllPostByGroupId(int groupId);

    public List<PostDto> getAllPostByUserID(Long userID);

    public Optional<PostDto> agreePost(String id, String currentUsername);

    public Optional<PostDto> disAgreePost(String id, String currentUsername);

    public Optional<PostDto> updatePost(PostDto postDto);

    public void deletePostById(String id);



}
