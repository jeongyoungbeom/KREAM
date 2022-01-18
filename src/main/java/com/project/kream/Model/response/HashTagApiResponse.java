package com.project.kream.Model.response;

import com.project.kream.Model.Entity.HashTag;
import lombok.*;

@Getter
public class HashTagApiResponse {
    private Long id;
    private String tagName;

    @Builder
    public HashTagApiResponse(HashTag hashTag) {
        this.id = hashTag.getId();
        this.tagName = hashTag.getTagName();
    }
}
