package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
}
