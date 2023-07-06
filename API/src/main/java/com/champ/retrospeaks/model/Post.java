package com.champ.retrospeaks.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
@Builder
@Data
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private Long userID;
    private String username;
    private int postType;
    private String title;
    private String content;
    private LocalDateTime postDate;
    private String category;
    private int groupId;


}
