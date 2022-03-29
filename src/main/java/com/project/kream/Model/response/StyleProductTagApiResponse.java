package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.ProductTag;
import lombok.*;

@Getter
public class StyleProductTagApiResponse {
    private Long productId;
    private String name;
    private String originFileName;
    private Long price;

    public StyleProductTagApiResponse(Product product, ProductTag productTag, Long price) {
        this.productId = productTag.getProduct().getId();
        this.name = product.getName();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.price = price;
    }
}

