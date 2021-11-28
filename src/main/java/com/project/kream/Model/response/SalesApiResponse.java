package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.SalesStatus1;
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

}
