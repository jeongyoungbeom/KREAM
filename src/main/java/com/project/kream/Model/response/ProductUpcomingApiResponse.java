package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ProductUpcomingApiResponse {
    private Long id;
    private String oringinFileName;
    private String brand;
    private String name;
    private LocalDateTime regdate;

    public ProductUpcomingApiResponse(Product product) {
        this.id = product.getId();
        this.oringinFileName = product.getProImgList().get(0).getOrigFileName();
        this.brand = product.getBrand();
        this.name = product.getName();
        this.regdate = product.getRegdate();
    }
}
