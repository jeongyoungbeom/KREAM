package com.project.kream.Model.request;

import com.project.kream.Model.Entity.HashTag;
import lombok.*;

@Getter
public class HashTagApiRequest {
    private Long id;
    private Long styleId;
    private String tagName;

    @Builder
    public HashTagApiRequest(String tagName) {
        this.tagName = tagName;
    }

    public HashTag toEntity(){
        return HashTag.builder()
                .tagName(tagName)
                .build();
    }
}
