package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status2 {
    대기중("STATUS_PURCHASE_BID", "PURCHASE_BID"),
    발송완료("STATUS_CONTINUE", "CONTINUE"),
    입고대기("STATUS_PURCHASE_BID", "PURCHASE_BID"),
    입고완료("STATUS_CONTINUE", "CONTINUE"),
    검수중("STATUS_PURCHASE_BID", "PURCHASE_BID"),
    검수보류("STATUS_CONTINUE", "CONTINUE"),
    검수합격("STATUS_PURCHASE_BID", "PURCHASE_BID"),
    배송중("STATUS_CONTINUE", "CONTINUE"),
    거래실패("STATUS_STOP", "STOP");

    private final String key;
    private final String title;
}
