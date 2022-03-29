package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.util.List;

@Getter
public class ProductDetailApiResponse {
    private Long id;
    private String brand;
    private String korName;
    private String name;
    private String collection;
    private String release;
    private Long releasePrice;
    private String modelNumber;
    private String color;
    private List<ProductSizeApiResponse> productSizeApiResponseList;
    private List<ProductPurchaseSizeApiResponse> productPurchaseSizeApiResponseList;
    private List<ProductSalesSizeApiResponse> productSalesSizeApiResponseList;
    private List<ProductCollectionApiResponse> productCollectionApiResponseList;
    private List<ProductStyleTagApiResponse> productStyleTagApiResponseList;
    private List<ProductTransactionApiResponse> productTransactionApiResponseList;
    private List<ProductImgApiResponse> productImgApiResponseList;
    private List<ProductTopTransactionApiResponse> productTopTransactionApiResponseList;

    public ProductDetailApiResponse(Product product, List<ProductSizeApiResponse> productSizeApiResponseList, List<ProductPurchaseSizeApiResponse> productPurchaseSizeApiResponseList, List<ProductSalesSizeApiResponse> productSalesSizeApiResponseList, List<ProductCollectionApiResponse> productCollectionApiResponseList, List<ProductStyleTagApiResponse> productStyleTagApiResponseList, List<ProductTransactionApiResponse> productTransactionApiResponseList, List<ProductImgApiResponse> productImgApiResponseList, List<ProductTopTransactionApiResponse> productTopTransactionApiResponseList) {
        this.id = product.getId();
        this.brand = product.getBrand();
        this.korName = product.getKorName();
        this.name = product.getName();
        this.collection = product.getCollection();
        this.release = product.getRelease();
        this.releasePrice = product.getReleasePrice();
        this.modelNumber = product.getModelNumber();
        this.color = product.getColor();
        this.productSizeApiResponseList = productSizeApiResponseList;
        this.productPurchaseSizeApiResponseList = productPurchaseSizeApiResponseList;
        this.productSalesSizeApiResponseList = productSalesSizeApiResponseList;
        this.productCollectionApiResponseList = productCollectionApiResponseList;
        this.productStyleTagApiResponseList = productStyleTagApiResponseList;
        this.productTransactionApiResponseList = productTransactionApiResponseList;
        this.productImgApiResponseList = productImgApiResponseList;
        this.productTopTransactionApiResponseList = productTopTransactionApiResponseList;
    }
}
