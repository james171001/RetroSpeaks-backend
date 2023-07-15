package com.champ.retrospeaks.repository;

import com.champ.retrospeaks.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findCommentByUserId(Long userId);

    List<Comment> findCommentByPostId(String postId);
}
