package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
}
