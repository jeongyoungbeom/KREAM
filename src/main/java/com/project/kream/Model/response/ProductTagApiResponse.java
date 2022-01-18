package com.project.kream.Model.response;


import com.project.kream.Model.Entity.ProductTag;
import lombok.*;

@Getter
public class ProductTagApiResponse {
    private Long id;
    private Long styleId;;
    private Long productId;
    private String originFileName;
    private Long releasePrice;
    private String name;

    public ProductTagApiResponse(ProductTag productTag) {
        this.id = productTag.getId();
        this.styleId = productTag.getStyle().getId();
        this.productId = productTag.getProduct().getId();
        this.originFileName = productTag.getProduct().getProImgList().get(0).getOrigFileName();
        this.releasePrice = productTag.getProduct().getReleasePrice();
        this.name = productTag.getProduct().getName();
    }
}
