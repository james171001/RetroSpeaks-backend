package com.champ.retrospeaks.mapper;


import com.champ.retrospeaks.dto.Post.PostDataDto;
import com.champ.retrospeaks.model.PostData;

import java.util.Optional;

public class toPostDataDto {

    public static PostDataDto toPostDataDto(Optional<PostData> postData){
        return PostDataDto.builder()
                .id(postData.get().getId())
                .interactionCount(postData.get().getInteractionCount())
                .commentCount(postData.get().getCommentCount())
                .build();
    }
}
