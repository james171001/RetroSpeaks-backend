package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Comment.CommentDto;
import com.champ.retrospeaks.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .updatedDate(comment.getUpdatedDate())
                .parentCommentID(comment.getParentCommentID())
                .postId(comment.getPostId())
                .build();
    }

    public static Comment toEntity(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .userId(commentDto.getUserId())
                .content(commentDto.getContent())
                .createdDate(commentDto.getCreatedDate())
                .updatedDate(commentDto.getUpdatedDate())
                .parentCommentID(commentDto.getParentCommentID())
                .postId(commentDto.getPostId())
                .build();
    }
}
