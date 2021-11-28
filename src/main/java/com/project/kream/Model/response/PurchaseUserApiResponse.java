package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseUserApiResponse {
    private Long orderNumber;
    private Long period;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private Long productId;
    private String productName;
    private String size;
    private String originFileName;
    private Long price;
    private LocalDateTime regdate;
    private String name;
    private String hp;
    private String zipcode;
    private String address1;
    private String address2;
    private String cardCompany;
    private String cardNumber;
    private String devCompany;
    private Long trackNum;
    private Long salesPrice;
    private Long purchasePrice;
}
