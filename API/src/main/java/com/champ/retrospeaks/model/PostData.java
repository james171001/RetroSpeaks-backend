package com.champ.retrospeaks.model;

import lombok.Data;

@Data
public class PostData {
    private int id;
    private int postType;
    private int interactionCount;
    private int commentCount;
}
