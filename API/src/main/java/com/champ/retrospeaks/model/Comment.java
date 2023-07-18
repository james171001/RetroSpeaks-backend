package com.champ.retrospeaks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Data
@Document(collection = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String commenter;
    private Long userId;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


    private String parentCommentID;

    private String postId;
}
