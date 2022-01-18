package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

@Getter
public class ProductDroppedApiResponse {
    private Long id;
    private String originFileName;
    private String brand;
    private String name;
    private Long price;

    public ProductDroppedApiResponse(Product product, Long price) {
        this.id = product.getId();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.brand = product.getBrand();
        this.name = product.getName();
        this.price = price;
    }
}
