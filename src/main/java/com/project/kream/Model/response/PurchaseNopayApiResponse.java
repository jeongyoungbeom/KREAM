package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PurchaseNopayApiResponse {
    private Long id;
    private Long customerId;
    private Long productId;
    private String userid;
    private String name;
    private LocalDateTime regdate;
    private PurchaseStatus2 purchaseStatus2;


    public PurchaseNopayApiResponse(Purchase purchase) {
        this.id = purchase.getId();
        this.customerId = purchase.getCustomer().getId();
        this.productId = purchase.getProduct().getId();
        this.userid = purchase.getCustomer().getUserid();
        this.name = purchase.getAddress().getName();
        this.regdate = purchase.getRegdate();
        this.purchaseStatus2  = purchase.getStatus2();
    }
}
