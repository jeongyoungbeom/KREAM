package com.project.kream.Model.response;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductSellFinishApiResponse {
    private String productOriginFileName;
    private Long price;
}
