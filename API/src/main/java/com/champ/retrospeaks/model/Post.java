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
    private String category;
    private int groupId;
    private int agreeCount;
    private int disagreeCount;


}
