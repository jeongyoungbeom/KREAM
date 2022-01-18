package com.project.kream.Model.response;

import lombok.*;

import java.util.List;

@Getter
public class MainApiResponse {
    private List<ProductDroppedApiResponse> productDroppedApiResponseList;
    private List<ProductUpcomingApiResponse> productUpcomingApiResponseList;
    private List<ProductPopularApiResponse> productPopularApiResponseList;
    private List<ProductCollectionApiResponse> productCollectionApiResponseList1;
    private List<ProductCollectionApiResponse> productCollectionApiResponseList2;
    private List<ProductCollectionApiResponse> productCollectionApiResponseList3;
    private List<ProductCollectionApiResponse> productCollectionApiResponseList4;
    private List<ProductCollectionApiResponse> productCollectionApiResponseList5;
    private List<ProductBrandApiResponse> productBrandApiResponseList1;
    private List<ProductBrandApiResponse> productBrandApiResponseList2;
    private List<ProductBrandApiResponse> productBrandApiResponseList3;
    private List<StylePickApiResponse> stylePickApiResponseList;
    private List<StyleHashTagListApiResponse> styleHashTagListApiResponseList;
    private List<ProductNewLowersApiResponse> productNewLowersApiResponseList;
    private List<ProductNewHighestApiResponse> productNewHighestApiResponseList;

    public MainApiResponse(List<ProductDroppedApiResponse> productDroppedApiResponseList, List<ProductUpcomingApiResponse> productUpcomingApiResponseList, List<ProductPopularApiResponse> productPopularApiResponseList, List<ProductCollectionApiResponse> productCollectionApiResponseList1, List<ProductCollectionApiResponse> productCollectionApiResponseList2, List<ProductCollectionApiResponse> productCollectionApiResponseList3, List<ProductCollectionApiResponse> productCollectionApiResponseList4, List<ProductCollectionApiResponse> productCollectionApiResponseList5, List<ProductBrandApiResponse> productBrandApiResponseList1, List<ProductBrandApiResponse> productBrandApiResponseList2, List<ProductBrandApiResponse> productBrandApiResponseList3, List<StylePickApiResponse> stylePickApiResponseList, List<StyleHashTagListApiResponse> styleHashTagListApiResponseList, List<ProductNewLowersApiResponse> productNewLowersApiResponseList, List<ProductNewHighestApiResponse> productNewHighestApiResponseList) {
        this.productDroppedApiResponseList = productDroppedApiResponseList;
        this.productUpcomingApiResponseList = productUpcomingApiResponseList;
        this.productPopularApiResponseList = productPopularApiResponseList;
        this.productCollectionApiResponseList1 = productCollectionApiResponseList1;
        this.productCollectionApiResponseList2 = productCollectionApiResponseList2;
        this.productCollectionApiResponseList3 = productCollectionApiResponseList3;
        this.productCollectionApiResponseList4 = productCollectionApiResponseList4;
        this.productCollectionApiResponseList5 = productCollectionApiResponseList5;
        this.productBrandApiResponseList1 = productBrandApiResponseList1;
        this.productBrandApiResponseList2 = productBrandApiResponseList2;
        this.productBrandApiResponseList3 = productBrandApiResponseList3;
        this.stylePickApiResponseList = stylePickApiResponseList;
        this.styleHashTagListApiResponseList = styleHashTagListApiResponseList;
        this.productNewLowersApiResponseList = productNewLowersApiResponseList;
        this.productNewHighestApiResponseList = productNewHighestApiResponseList;
    }
}
