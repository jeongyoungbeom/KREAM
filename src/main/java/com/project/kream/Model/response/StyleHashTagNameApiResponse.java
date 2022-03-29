package com.project.kream.Model.response;

import com.project.kream.Model.Entity.StyleHashTag;
import lombok.*;

@Getter
public class StyleHashTagNameApiResponse {
    private String tagName;

    public StyleHashTagNameApiResponse(StyleHashTag styleHashTag) {
        this.tagName = styleHashTag.getHashTag().getTagName();
    }
}
