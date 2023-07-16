package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Comment.CommentDto;

public interface CommentService {

    void create (CommentDto commentDto, String postId);
    void update (CommentDto commentDto);

    void delete (String commentId);

}
