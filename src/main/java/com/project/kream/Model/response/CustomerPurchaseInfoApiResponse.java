package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerPurchaseInfoApiResponse {
    private Long id;
    private Long productId;
    private String name;
    private String originFileName;
    private Long price;
    private String size;
    private Long period;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;
    private LocalDateTime regdate;

}
