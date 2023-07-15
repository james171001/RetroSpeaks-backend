package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Post.PostDataDto;
import com.champ.retrospeaks.model.PostData;

import java.util.Optional;

public interface PostDataService {
    public PostDataDto savePostData(PostDataDto postDataDto);

    public void updatePostDataInteraction(String postId) throws Exception;

    public Optional<PostData> findPostDataById(String id);
}
