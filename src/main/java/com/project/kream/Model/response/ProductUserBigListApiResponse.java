package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.Getter;

@Getter
public class ProductUserBigListApiResponse {
    private Long id;
    private String brand;
    private Category category;
    private SubCategory subCategory;
    private String korName;
    private String name;
    private String origFileName;
    private Long price;

    public ProductUserBigListApiResponse(Product product, Long price) {
        this.id = product.getId();
        this.brand = product.getBrand();
        this.category = product.getCategory();
        this.subCategory = product.getSubCategory();
        this.korName = product.getKorName();
        this.name = product.getName();
        this.origFileName = product.getName();
        this.price = price;
    }
}
