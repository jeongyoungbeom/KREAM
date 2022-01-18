package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.*;

import java.util.List;

@Getter
public class ProductUserListApiResponse {
    private Long id;
    private String brand;
    private Category category;
    private SubCategory subCategory;
    private String korName;
    private String name;
    private String origFileName;
    private Long price;
    private Long amount;
    private Long mini;
    private Long maxx;
    private Long premium;

    public ProductUserListApiResponse(ProductApiResponse productApiResponse, Long price) {
        this.id = productApiResponse.getId();
        this.brand = productApiResponse.getBrand();
        this.category = productApiResponse.getCategory();
        this.subCategory = productApiResponse.getSubCategory();
        this.korName = productApiResponse.getKorName();
        this.name = productApiResponse.getName();
        this.origFileName = productApiResponse.getName();
        this.price = price;
        this.amount = productApiResponse.getAmount();
        this.mini = productApiResponse.getMini();
        this.maxx = productApiResponse.getMaxx();
        this.premium = productApiResponse.getPremium();
    }
}
