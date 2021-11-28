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
public class ProductSellFinalResponse {
    private Long id;
    private String name;
    private String korName;
    private String originFileName;
    private String size;
    private String modelNumber;
    private String accountName;
    private Long accountId;
    private Long price;
    private String bank;
    private String accountNumber;
    private List<ProductAddressApiResponse> productAddressApiResponseList;
}
