package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.Post;

import java.util.List;


public interface PostService {
    void savePost(PostDto postDto);

    public List<PostDto> getAllPostByGroupId(int groupId);
}
