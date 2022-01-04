package com.project.kream.Model.request;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import com.project.kream.Model.response.AccountApiResponse;
import com.project.kream.Model.response.CardInfoApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PurchaseApiRequest {
    private Long id;
    private Long productId;
    private Long customerId;
    private Long addressId;
    private Long cardInfo;
    private Long price;
    private Long period;
    private String sizeType;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;
    private Long SalasId;
    private String regdate1;
    private String regdate2;

    public Purchase toEntity(Product product, Customer customer,  Address address, CardInfo card){
        return Purchase.builder()
                .price(price)
                .period(period)
                .sizeType(sizeType)
                .status1(status1)
                .status2(status2)
                .status3(status3)
                .product(product)
                .customer(customer)
                .address(address)
                .cardInfo(card)
                .build();
    }

    public Purchase toEntityTo(Product product, Customer customer, Sales sales, Address address, CardInfo cardInfo){
        return Purchase.builder()
                .product(product)
                .customer(customer)
                .sales(sales)
                .price(price)
                .period(period)
                .sizeType(sizeType)
                .status1(status1)
                .status2(status2)
                .status3(status3)
                .address(address)
                .cardInfo(cardInfo)
                .build();
    }

}
