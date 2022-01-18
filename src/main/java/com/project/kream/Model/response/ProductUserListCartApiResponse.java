package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.util.List;

@Getter
public class ProductUserListCartApiResponse {
    private Long id;
    private String korName;
    private String name;
    private String origFileName;
    private List<ProductSizeApiResponse> productSizeApiResponseList;

    public ProductUserListCartApiResponse(Product product, List<ProductSizeApiResponse> productSizeApiResponseList) {
        this.id = id;
        this.korName = korName;
        this.name = name;
        this.origFileName = origFileName;
        this.productSizeApiResponseList = productSizeApiResponseList;
    }
}
