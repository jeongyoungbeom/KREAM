package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

@Getter
public class ProductSellInfoApiResponse {
    private Long id;
    private String modelNumber;
    private String name;
    private String korName;
    private String origFileName;
    private String size;
    private Long salesPrice;
    private Long purchasePrice;
    private Long purchaseId;

    public ProductSellInfoApiResponse(Product product, String size, Long salesPrice, Long purchasePrice, Long purchaseId) {
        this.id = product.getId();
        this.modelNumber = product.getModelNumber();
        this.name = product.getName();
        this.korName = product.getKorName();
        this.origFileName = product.getProImgList().get(0).getOrigFileName();
        this.size = size;
        this.salesPrice = salesPrice;
        this.purchasePrice = purchasePrice;
        this.purchaseId = purchaseId;
    }
}
