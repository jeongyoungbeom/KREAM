package com.project.kream.Model.response;

import lombok.*;

@Getter
public class DashBoardApiResponse {
    private Long customerCnt;
    private Long productCnt;
    private Long styleCnt;
    private Long transactionCnt;
    private Long nowCustomerCnt;
    private Long nowWithdrawalCnt;
    private Long nowTransactionCnt;
    private String deliveryCnt;
    private String completedCnt;
    private String waitingCnt;

    public DashBoardApiResponse(Long customerCnt, Long productCnt, Long styleCnt, Long transactionCnt, Long nowCustomerCnt, Long nowWithdrawalCnt, Long nowTransactionCnt, String deliveryCnt, String completedCnt, String waitingCnt) {
        this.customerCnt = customerCnt;
        this.productCnt = productCnt;
        this.styleCnt = styleCnt;
        this.transactionCnt = transactionCnt;
        this.nowCustomerCnt = nowCustomerCnt;
        this.nowWithdrawalCnt = nowWithdrawalCnt;
        this.nowTransactionCnt = nowTransactionCnt;
        this.deliveryCnt = deliveryCnt;
        this.completedCnt = completedCnt;
        this.waitingCnt = waitingCnt;
    }
}
