package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Delivery;
import com.project.kream.Model.enumclass.DeliveryStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class DeliveryApiResponse {
    private Long id;
    private Long purchaseId;
    private DeliveryStatus deliveryStatus;
    private String devCompany;
    private Long trackNum;
    private LocalDateTime regdate;

    public DeliveryApiResponse(Delivery delivery) {
        this.id = delivery.getId();
        this.purchaseId = delivery.getPurchase().getId();
        this.deliveryStatus = delivery.getDeliveryStatus();
        this.devCompany = delivery.getDevCompany();
        this.trackNum = delivery.getTrackNum();
        this.regdate = delivery.getRegdate();
    }
}
