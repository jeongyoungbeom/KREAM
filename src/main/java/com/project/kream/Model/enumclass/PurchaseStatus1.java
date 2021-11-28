package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseStatus1 {

    종료("END","END"),
    진행중("CONTINUE","CONTINUE"),
    구매입찰("PURCHASE_BIDDING","PURCHASE_BIDDING");

    private final String status;
    private final String title;
}
