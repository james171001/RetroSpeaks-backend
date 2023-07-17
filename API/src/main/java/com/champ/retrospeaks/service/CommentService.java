package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Comment.CommentDto;

import java.util.List;

public interface CommentService {

    void create (CommentDto commentDto, String postId);
    void update (CommentDto commentDto);

    void delete (String commentId);

    List<CommentDto> getAllCommentsByPostId(String postId);



}
