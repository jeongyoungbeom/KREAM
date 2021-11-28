package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPurchaseApiResponse {
    private DeliveryApiResponse deliveryApiResponse;
    private ProductApiResponse productApiResponse;
    private AddressApiResponse addressApiResponse;
    private String email;
    private String hp;
    private String size;

}
