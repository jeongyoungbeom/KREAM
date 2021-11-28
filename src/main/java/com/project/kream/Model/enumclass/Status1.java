package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status1 {
    구매입찰("STATUS_PURCHASE_BID", "PURCHASE_BID"),
    진행중("STATUS_CONTINUE", "CONTINUE"),
    종료("STATUS_STOP", "STOP");

    private final String key;
    private final String title;
}
