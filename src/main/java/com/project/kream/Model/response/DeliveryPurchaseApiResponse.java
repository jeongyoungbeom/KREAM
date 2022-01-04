package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Purchase;
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
    private DeliveryProductApiResponse deliveryProductApiResponse;
    private AddressApiResponse addressApiResponse;
    private String email;
    private String hp;
    private String size;

    public DeliveryPurchaseApiResponse(DeliveryApiResponse deliveryApiResponse, DeliveryProductApiResponse deliveryProductApiResponse, AddressApiResponse addressApiResponse, Customer customer, Purchase purchase) {
        this.deliveryApiResponse = deliveryApiResponse;
        this.deliveryProductApiResponse = deliveryProductApiResponse;
        this.addressApiResponse = addressApiResponse;
        this.email = customer.getEmail();
        this.hp = customer.getHp();
        this.size = purchase.getSizeType();
    }
}
