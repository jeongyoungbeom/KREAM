package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.SalesStatus1;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductSalesSizeApiResponse {
    private String size;
    private Long price;
    private Long cnt;
    private SalesStatus1 salesStatus1;
}
