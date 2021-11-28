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

}
