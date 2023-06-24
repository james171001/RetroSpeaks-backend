package com.champ.retrospeaks.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userID;
    private int postID;
    private String content;
    @CreationTimestamp
    private LocalDateTime commentDate;
    private int parentCommentID;
}
