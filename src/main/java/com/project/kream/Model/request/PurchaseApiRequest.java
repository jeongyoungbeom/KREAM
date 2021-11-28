package com.project.kream.Model.request;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import com.project.kream.Model.response.AccountApiResponse;
import com.project.kream.Model.response.CardInfoApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PurchaseApiRequest {
    private Long id;
    private Long productId;
    private Long customerId;
    private Long addressId;
    private Long cardId;
    private Long price;
    private Long period;
    private String sizeType;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;
    private Long SalasId;
    private String regdate1;
    private String regdate2;

}
