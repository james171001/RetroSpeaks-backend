package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Post.PostDataDto;
import com.champ.retrospeaks.model.PostData;
import com.champ.retrospeaks.repository.PostDataRepository;
import com.champ.retrospeaks.repository.PostRepository;
import com.champ.retrospeaks.service.PostDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostDataServiceImpl implements PostDataService {

    PostRepository postRepository;
    PostDataRepository postDataRepository;

    @Autowired
    public PostDataServiceImpl(PostRepository postRepository, PostDataRepository postDataRepository) {
        this.postRepository = postRepository;
        this.postDataRepository = postDataRepository;
    }

    @Override
    public PostDataDto savePostData(PostDataDto postDataDto) {
        PostData postData = new PostData();
        postData.setId(postDataDto.getId());
        postData.setInteractionCount(postDataDto.getInteractionCount());
        postData.setCommentCount(postDataDto.getCommentCount());
        postDataRepository.save(postData);
        return postDataDto;
    }

    @Override
    public void updatePostDataInteraction(String postId) throws Exception {
        Optional<PostData> existingPostData = postDataRepository.findById(postId);
        if (existingPostData.isPresent()) {
            PostData postData = existingPostData.get();
            postData.setInteractionCount(postData.getInteractionCount() + 1);

            postDataRepository.save(postData);
        } else {
            // Handle the case when the post data is not found
            throw new Exception("Post data not found for postId: " + postId);
        }
    }

    @Override
    public Optional<PostData> findPostDataById(String id) {
        Optional<PostData> postData = postDataRepository.findById(id);
        return postData;
    }
}
