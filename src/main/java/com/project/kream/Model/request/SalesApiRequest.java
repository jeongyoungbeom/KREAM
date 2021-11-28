package com.project.kream.Model.request;

import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import com.project.kream.Model.response.AccountApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SalesApiRequest {
    private Long id;
    private Long productId;
    private Long customerId;
    private Long addressId;
    private Long accountId;
    private Long price;
    private Long period;
    private String sizeType;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
    private Long purchaseId;
    private AccountApiResponse accountApiResponse;
    private String regdate1;
    private String regdate2;
}
