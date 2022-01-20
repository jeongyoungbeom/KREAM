package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class SalesListApiResponse {
    private Long productId;
    private Long salesId;
    private LocalDateTime regdate;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
    private String name;

    public SalesListApiResponse(Long productId ,Sales sales) {
        this.productId = productId;
        this.salesId = sales.getId();
        this.regdate = sales.getRegdate();
        this.status2 = sales.getStatus2();
        this.status3 = sales.getStatus3();
    }
}
