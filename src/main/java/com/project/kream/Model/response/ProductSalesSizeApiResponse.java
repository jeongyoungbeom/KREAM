package com.project.kream.Model.response;

import lombok.*;

@Getter
public class ProductSalesSizeApiResponse {
    private String size;
    private Long price;
    private Long cnt;

    public ProductSalesSizeApiResponse(String size, Long price, Long cnt) {
        this.size = size;
        this.price = price;
        this.cnt = cnt;
    }
}
