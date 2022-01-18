package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import lombok.*;

@Getter
public class ProductPurchaseSizeApiResponse {
    private String size;
    private Long price;
    private Long cnt;

    public ProductPurchaseSizeApiResponse(String size, Long price, Long cnt) {
        this.size = size;
        this.price = price;
        this.cnt = cnt;
    }
}
