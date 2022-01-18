package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.util.List;

@Getter
public class ProductCollectionApiResponse {
    private Long id;
    private String oringinFileName;
    private String brand;
    private String name;
    private Long price;

    public ProductCollectionApiResponse(Product product, Long price) {
        this.id = product.getId();
        this.oringinFileName = product.getName();
        this.brand = product.getBrand();
        this.name = product.getName();
        this.price = price;
    }
}
