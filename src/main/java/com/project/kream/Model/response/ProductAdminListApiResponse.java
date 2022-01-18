package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ProductAdminListApiResponse {
    private Long id;
    private String modelNumber;
    private String brand;
    private Category category;
    private PostStatus postStatus;
    private LocalDateTime regdate;

    public ProductAdminListApiResponse(Product product) {
        this.id = product.getId();
        this.modelNumber = product.getModelNumber();
        this.brand = product.getBrand();
        this.category = product.getCategory();
        this.postStatus = product.getPostStatus();
        this.regdate = product.getRegdate();
    }
}
