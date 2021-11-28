package com.project.kream.Model.response;

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
}
