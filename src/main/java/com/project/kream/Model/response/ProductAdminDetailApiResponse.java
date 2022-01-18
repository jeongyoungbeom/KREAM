package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
public class ProductAdminDetailApiResponse {
    private Long id;
    private String brand;
    private String korName;
    private String name;
    private String collection;
    private String release;
    private String gender;
    private Long releasePrice;
    private String modelNumber;
    private String color;
    private List<ProimgPathApiResponse> proimgPathApiResponseList;
    private Category category;
    private SubCategory subCategory;
    private PostStatus postStatus;

    public ProductAdminDetailApiResponse(Product product, List<ProimgPathApiResponse> proimgPathApiResponseList) {
        this.id = product.getId();
        this.brand = product.getBrand();
        this.korName = product.getKorName();
        this.name = product.getName();
        this.collection = product.getCollection();
        this.release = product.getRelease();
        this.gender = product.getGender();
        this.releasePrice = product.getReleasePrice();
        this.modelNumber = product.getModelNumber();
        this.color = product.getColor();
        this.proimgPathApiResponseList = proimgPathApiResponseList;
        this.category = product.getCategory();
        this.subCategory = product.getSubCategory();
        this.postStatus = product.getPostStatus();
    }
}
