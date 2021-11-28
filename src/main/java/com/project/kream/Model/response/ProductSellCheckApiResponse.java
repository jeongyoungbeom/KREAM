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
public class ProductSellCheckApiResponse {
    private Long id;
    private String modelNumber;
    private String name;
    private String korName;
    private String size;
    private String origFileName;
    private List<ProductSellSizeApiResponse> productSellSizeApiResponseList;
}
