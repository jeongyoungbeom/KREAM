package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.util.List;

@Getter
public class ProductSellCheckApiResponse {
    private Long id;
    private String modelNumber;
    private String name;
    private String korName;
    private String size;
    private String origFileName;
    private List<ProductSellSizeApiResponse> productSellSizeApiResponseList;

    public ProductSellCheckApiResponse(Product product, String size, List<ProductSellSizeApiResponse> productSellSizeApiResponseList) {
        this.id = product.getId();
        this.modelNumber = product.getModelNumber();
        this.name = product.getName();
        this.korName = product.getKorName();
        this.size = size;
        this.origFileName = product.getProImgList().get(0).getOrigFileName();
        this.productSellSizeApiResponseList = productSellSizeApiResponseList;
    }
}
