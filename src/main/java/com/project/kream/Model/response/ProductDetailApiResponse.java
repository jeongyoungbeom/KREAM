package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private List<ProductRegdateApiResponse> productRegdateApiResponseList;
    private List<ProductImgApiResponse> productImgApiResponseList;
    private List<ProductTopTransactionApiResponse> productTopTransactionApiResponseList;
}
