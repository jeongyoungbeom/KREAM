package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PurchaseListApiResponse {
    private Long productId;
    private Long purchaseId;
    private LocalDateTime regdate;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;
    private String name;

    public PurchaseListApiResponse(Purchase purchase) {
        this.productId = purchase.getProduct().getId();
        this.purchaseId = purchase.getId();
        this.regdate = purchase.getRegdate();
        this.status2 = purchase.getStatus2();
        this.status3 = purchase.getStatus3();
        this.name = purchase.getAddress().getName();
    }
}
