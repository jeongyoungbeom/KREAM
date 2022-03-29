package com.project.kream.Model.response;

import com.project.kream.Model.Entity.ProductTag;
import lombok.*;

@Getter
public class StyleProductTagIdApiResponse {
    private Long productId;
    private String originFileName;
    private String name;

    public StyleProductTagIdApiResponse(ProductTag productTag) {
        this.productId = productTag.getProduct().getId();
        this.originFileName = productTag.getProduct().getProImgList().get(0).getOrigFileName();
        this.name = productTag.getProduct().getName();
    }
}
