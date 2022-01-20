package com.project.kream.Model.response;

import com.project.kream.Model.Entity.StyleHashTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleHashTagNameApiResponse {
    private String tagName;

    public StyleHashTagNameApiResponse(StyleHashTag styleHashTag) {
        this.tagName = styleHashTag.getHashTag().getTagName();
    }
}
