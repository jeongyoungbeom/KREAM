package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SalesListApiResponse {
    private Long productId;
    private Long salesId;
    private LocalDateTime regdate;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
    private String name;
}
