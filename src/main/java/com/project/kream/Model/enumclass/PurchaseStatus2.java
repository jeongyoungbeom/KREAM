package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseStatus2 {
    입찰중("BIDDING","BIDDING"),
    미결제("NOTCOMPLETE","NOTCOMPLETE"),
    기한만료("EXPIRATION","EXPIRATION"),
    발송완료("SHIP_COMPLETE","SHIP_COMPLETE"),
    입고대기("PUT_WAIT","PUT_WAIT"),
    입고완료("PUT_COMPLETE","PUT_COMPLETE"),
    검수중("INSPECTING","INSPECTING"),
    검수보류("INSPECT_HOLD","INSPECT_HOLD"),
    검수합격("INSPECT_ACCEPT","INSPECT_ACCEPT"),
    배송중("DELIVERY","DELIVERY"),
    배송완료("DELIVERY_COMPLETE","DELIVERY_COMPLETE"),
    취소완료("CANCEL_COMPLETE","CANCEL_COMPLETE"),
    거래실패("FAIL","FAIL");

    private final String status;
    private final String title;
}
