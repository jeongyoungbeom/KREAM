package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

@Getter
public class StyleSearchListApiResponse {
    private Long productId;
    private String name;
    private String korName;
    private String origFileName;
    private Long price;

    public StyleSearchListApiResponse(Product product, Long price) {
        this.productId = product.getId();
        this.name = product.getName();
        this.korName = product.getKorName();
        this.origFileName = product.getProImgList().get(0).getOrigFileName();
        this.price = price;
    }
}
