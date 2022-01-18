package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.Getter;

@Getter
public class ProductBrandApiResponse {
    private Long id;
    private String oringinFileName;
    private String brand;
    private String name;
    private Long price;

    public ProductBrandApiResponse(Product product, Long price) {
        this.id = product.getId();
        this.oringinFileName = product.getProImgList().get(0).getOrigFileName();
        this.brand = product.getBrand();
        this.name = product.getName();
        this.price = price;
    }
}
