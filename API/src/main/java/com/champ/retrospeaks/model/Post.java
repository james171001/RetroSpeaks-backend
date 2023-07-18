package com.champ.retrospeaks.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private List<Comment> comments =new ArrayList<>();

    private String category;
    private int groupId;
    private int agreeCount;
    private int disagreeCount;
    @Nullable
    private List<Long> voterList;


}
