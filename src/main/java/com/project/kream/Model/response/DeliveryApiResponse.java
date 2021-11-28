package com.project.kream.Model.response;

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
public class DeliveryApiResponse {
    private Long id;
    private Long purchaseId;
    private DeliveryStatus deliveryStatus;
    private String devCompany;
    private Long trackNum;
    private LocalDateTime regdate;
    private PurchaseApiResponse purchaseApiResponse;
}
