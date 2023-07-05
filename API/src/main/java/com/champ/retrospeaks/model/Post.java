package com.champ.retrospeaks.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private int userID;
    private int postType;
    private String title;
    private String content;
    @CreationTimestamp
    private LocalDateTime postDate;
    private String category;
}
