package com.champ.retrospeaks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Document(collection = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Long userID;
    private String username;
    private int postType;
    private String title;
    private String content;
    private LocalDateTime postDate;

    @DocumentReference(lazy = true)
    private List<Comment> comments;

    private String category;
    private int groupId;
    private int agreeCount;
    private int disagreeCount;


}
