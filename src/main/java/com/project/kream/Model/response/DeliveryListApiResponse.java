package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Delivery;
import com.project.kream.Model.enumclass.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DeliveryListApiResponse {
    private Long deliveryId;
    private Long purchaseId;
    private DeliveryStatus deliveryStatus;
    private String devCompany;
    private Long trackNum;
    private LocalDateTime regdate;

    public DeliveryListApiResponse(Delivery delivery) {
        this.deliveryId = delivery.getId();
        this.purchaseId = delivery.getPurchase().getId();
        this.deliveryStatus = delivery.getDeliveryStatus();
        this.devCompany = delivery.getDevCompany();
        this.trackNum = delivery.getTrackNum();
        this.regdate = delivery.getRegdate();
    }
}
