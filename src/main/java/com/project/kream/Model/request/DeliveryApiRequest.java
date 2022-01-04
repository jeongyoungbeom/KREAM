package com.project.kream.Model.request;


import com.project.kream.Model.Entity.Delivery;
import com.project.kream.Model.Entity.Purchase;
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
public class DeliveryApiRequest {
    private Long id;
    private Long purchaseId;
    private DeliveryStatus deliveryStatus;
    private String devCompany;
    private Long trackNum;
    private String regdate1;
    private String regdate2;

    public Delivery toEntity(Purchase purchase){
        return Delivery.builder()
                .deliveryStatus(deliveryStatus)
                .devCompany(devCompany)
                .trackNum(trackNum)
                .purchase(purchase)
                .build();
    }
}
