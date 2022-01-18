package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

@Getter
public class ProductSearchListApiResponse {
    private Long productId;
    private String name;
    private String korName;
    private String origFileName;

    public ProductSearchListApiResponse(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.korName = product.getKorName();
        this.origFileName = product.getName();
    }
}
