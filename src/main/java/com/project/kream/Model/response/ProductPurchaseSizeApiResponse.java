package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductPurchaseSizeApiResponse {
    private String size;
    private Long price;
    private Long cnt;
    private PurchaseStatus1 purchaseStatus1;
}
