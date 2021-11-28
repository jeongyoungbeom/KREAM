package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerMypagePurchaseApiResponse {
    private Long id;
    private String name;
    private String size;
    private String originFileName;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;
}
