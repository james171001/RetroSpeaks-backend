package com.champ.retrospeaks.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String id;
    private String commenter;
    private Long userId;
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String parentCommentID;
    private String postId;

    public void setCommentDate() {
        this.createdDate = LocalDateTime.now();
//        this.updatedDate = LocalDateTime.now();
    }

}

