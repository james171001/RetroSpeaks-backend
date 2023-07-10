package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Comment.CommentForCreationDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;

public interface CommentService {

    void create (CommentForCreationDto commentForCreationDto, String userName, String postId);

    void update (GroupForCreationDto groupForCreationDto, String owner, Long groupId);

}
