package com.project.kream.Model.response;

import lombok.*;

@Getter
public class ProductHashtagApiResponse {
    private String tagName;

    public ProductHashtagApiResponse(String tagName) {
        this.tagName = tagName;
    }
}
