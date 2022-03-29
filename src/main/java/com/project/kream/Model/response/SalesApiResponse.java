package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class SalesApiResponse implements Comparable<PurchaseApiResponse>{

    @Override
    public int compareTo(PurchaseApiResponse o) {
        return 0;
    }

    private Long id;
    private Long productId;
    private Long customerId;
    private Long price;
    private Long period;
    private String sizeType;
    private String originFileName;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
    private LocalDateTime regdate;

    private AccountApiResponse accountApiResponse;
    private CustomerApiResponse customerApiResponse;
    private AddressApiResponse addressApiResponse;
    private CardInfoApiResponse cardInfoApiResponse;
    private ProductApiResponse productApiResponse;
    private DeliveryApiResponse deliveryApiResponse;


    public SalesApiResponse(Sales sales) {
        this.id = sales.getId();
        this.productId = sales.getProduct().getId();
        this.customerId = sales.getCustomer().getId();
        this.price = sales.getPrice();
        this.period = sales.getPeriod();
        this.sizeType = sales.getSizeType();
        this.originFileName = sales.getProduct().getProImgList().get(0).getFilePath();
        this.status1 = sales.getStatus1();
        this.status2 = sales.getStatus2();
        this.status3 = sales.getStatus3();
        this.regdate = sales.getRegdate();
    }
}
