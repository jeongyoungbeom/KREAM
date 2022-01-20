package com.project.kream.Model.request;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import com.project.kream.Model.response.AccountApiResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class SalesApiRequest {
    private Long id;
    private Long productId;
    private Long customerId;
    private Long addressId;
    private Long accountId;
    private Long price;
    private Long period;
    private String sizeType;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
    private Long purchaseId;
    private AccountApiResponse accountApiResponse;
    private String regdate1;
    private String regdate2;

    public Sales toEntity(Product product, Customer customer, Address address, Account account){
        return Sales.builder()
                .product(product)
                .customer(customer)
                .address(address)
                .account(account)
                .price(price)
                .period(period)
                .sizeType(sizeType)
                .status1(status1)
                .status2(status2)
                .status3(status3)
                .build();
    }
    public Sales toEntity(Product product, Customer customer, Address address, Account account, Purchase purchase){
        return Sales.builder()
                .product(product)
                .customer(customer)
                .address(address)
                .account(account)
                .price(price)
                .period(period)
                .sizeType(sizeType)
                .status1(status1)
                .status2(status2)
                .status3(status3)
                .purchase(purchase)
                .build();
    }
}
