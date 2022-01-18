package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.util.List;

@Getter
public class ProductBuyFinalApiResponse {
    private Long id;
    private String name;
    private String korName;
    private String modelNumber;
    private String originFileName;
    private String size;
    private Long price;
    private List<ProductAddressApiResponse> productAddressApiResponseList;
    private List<ProductCardInfoApiResponse> productCardInfoApiResponses;
    private Long date;

    public ProductBuyFinalApiResponse(Product product, Long ProductId, String size, Long price, List<ProductAddressApiResponse> productAddressApiResponseList, List<ProductCardInfoApiResponse> productCardInfoApiResponses, Long date) {
        this.id = ProductId;
        this.name = product.getName();
        this.korName = product.getKorName();
        this.modelNumber = product.getModelNumber();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.size = size;
        this.price = price;
        this.productAddressApiResponseList = productAddressApiResponseList;
        this.productCardInfoApiResponses = productCardInfoApiResponses;
        this.date = date;
    }
}
