package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Comment.CommentDto;

public interface CommentService {

    void create (CommentDto commentDto);
    void update (CommentDto commentDto);

    void delete (String commentId);

}
