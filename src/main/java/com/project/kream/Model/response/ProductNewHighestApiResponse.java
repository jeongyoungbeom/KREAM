package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

@Getter
public class ProductNewHighestApiResponse {
    private Long id;
    private String oringinFileName;
    private String brand;
    private String name;
    private Long price;

    public ProductNewHighestApiResponse(Product product, Long price) {
        this.id = product.getId();
        this.oringinFileName = product.getProImgList().get(0).getOrigFileName();
        this.brand = product.getBrand();
        this.name = product.getName();
        this.price = price;
    }
}
