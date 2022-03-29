package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

@Getter
public class StyleDetailProductTagApiResponse {
    private Long id;
    private String origFileName;
    private String name;
    private Long price;

    public StyleDetailProductTagApiResponse(Product product, Long price) {
        this.id = product.getId();
        this.origFileName = product.getProImgList().get(0).getOrigFileName();
        this.name = product.getName();
        this.price = price;
    }
}

