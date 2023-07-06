package com.champ.retrospeaks.dto.Post;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {
    private String id;
    private Long userID;
    private String username;
    private int postType;
    private String title;
    private String content;
    @Field("postDate")
    private LocalDateTime postDate;
    private String category;
    private int groupId;

    public void setCurrentPostDate() {
        this.postDate = LocalDateTime.now();
    }
}
