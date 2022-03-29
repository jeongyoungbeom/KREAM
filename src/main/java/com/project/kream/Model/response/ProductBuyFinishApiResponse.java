package com.project.kream.Model.response;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductBuyFinishApiResponse {
    private String productOriginFileName;
    private Long price;
}
