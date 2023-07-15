package com.champ.retrospeaks.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDataDto {
    private String id;
    private int interactionCount;
    private int commentCount;
}
