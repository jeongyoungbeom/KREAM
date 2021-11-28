package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
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
public class SalesUserApiResponse {
    private Long orderNumber;
    private Long period;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private Long productId;
    private String productName;
    private String size;
    private String originFileName;
    private Long price;
    private LocalDateTime regdate;
    private String bank;
    private String accountNumber;
    private String cardCompany;
    private String cardNumber;
    private String name;
    private String hp;
    private String zipcode;
    private String address1;
    private String address2;
    private Long salesPrice;
    private Long purchasePrice;
}
