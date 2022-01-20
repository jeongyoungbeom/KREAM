package com.project.kream.Model.response;

import com.project.kream.Model.Entity.HashTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleDetailHashTagApiResponse {
    private String tagName;

    public StyleDetailHashTagApiResponse(String tagName) {
        this.tagName = tagName;
    }
}

