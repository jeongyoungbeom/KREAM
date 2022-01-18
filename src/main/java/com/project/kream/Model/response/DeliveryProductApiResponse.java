package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.enumclass.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DeliveryProductApiResponse {
    private Long id;
    private String brand;
    private String collection;
    private Category category;
    private String korName;
    private String name;
    private String gender;
    private String release;
    private Long releasePrice;
    private String modelNumber;
    private String color;

    public DeliveryProductApiResponse(Product product) {
        this.id = product.getId();
        this.brand = product.getBrand();
        this.collection = product.getCollection();
        this.category = product.getCategory();
        this.korName = product.getKorName();
        this.name = product.getName();
        this.gender = product.getGender();
        this.release = product.getRelease();
        this.releasePrice = product.getReleasePrice();
        this.modelNumber = product.getModelNumber();
        this.color = product.getColor();
    }
}
