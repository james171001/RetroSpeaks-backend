package com.champ.retrospeaks.dto.Post;


import com.champ.retrospeaks.dto.Comment.CommentDto;
import com.champ.retrospeaks.dto.User.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    private List<CommentDto> comments = new ArrayList<>();
    private int groupId;
    private int agreeCount;
    private int disagreeCount;

    public void setCurrentPostDate() {
        this.postDate = LocalDateTime.now();
    }
}
