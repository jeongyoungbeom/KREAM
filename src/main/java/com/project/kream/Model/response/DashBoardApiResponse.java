package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DashBoardApiResponse {
    private Long CustomerCnt;
    private Long ProductCnt;
    private Long StyleCnt;
    private Long TransactionCnt;
    private Long NowCustomerCnt;
    private Long NowWithdrawalCnt;
    private Long NowTransactionCnt;
    private String deliveryCnt;
    private String completedCnt;
    private String waitingCnt;
}
