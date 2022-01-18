package com.project.kream.Model.response;

import lombok.*;

@Getter
public class ProductBuySizeApiResponse {
    private String size;
    private Long cnt;

    public ProductBuySizeApiResponse(String size, Long cnt) {
        this.size = size;
        this.cnt = cnt;
    }
}
