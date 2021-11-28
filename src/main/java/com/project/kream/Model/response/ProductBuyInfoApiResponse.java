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
public class ProductBuyInfoApiResponse {
    private Long id;
    private String modelNumber;
    private String name;
    private String korName;
    private String origFileName;
    private String size;
    private Long salesPrice;
    private Long purchasePrice;
    private Long salesId;
}
