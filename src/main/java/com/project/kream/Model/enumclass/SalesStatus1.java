package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SalesStatus1 {
    종료("END","END"),
    진행중("CONTINUE","CONTINUE"),
    판매입찰("SALES_BIDDING","SALES_BIDDING");

    private final String status;
    private final String title;
}
