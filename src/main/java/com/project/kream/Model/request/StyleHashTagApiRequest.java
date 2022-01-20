package com.project.kream.Model.request;

import com.project.kream.Model.Entity.HashTag;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleHashTag;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class StyleHashTagApiRequest {
    private Long styleId;
    private Long hashTagId;

    public StyleHashTag toEntity(Style style, HashTag hashTag){
        return StyleHashTag.builder()
                .style(style)
                .hashTag(hashTag)
                .build();
    }
}
