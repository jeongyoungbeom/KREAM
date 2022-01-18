package com.project.kream.Model.response;

import lombok.*;

@Getter
public class ProductSellSizeApiResponse {
    private String size;
    private Long cnt;

    public ProductSellSizeApiResponse(String size, Long cnt) {
        this.size = size;
        this.cnt = cnt;
    }
}
