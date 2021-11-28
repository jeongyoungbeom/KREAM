package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PurchaseApiResponse implements Comparable<PurchaseApiResponse>{
    @Override //객체를 비교하기 위해 오버라이딩 comparable
    public int compareTo(PurchaseApiResponse o) {
        return 0;
    }
    private Long id;
    private Long productId;
    private Long customerId;
    private Long price;
    private Long period;
    private String proimg;
    private String sizeType;
    private String email;
    private String address;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;
    private LocalDateTime regdate;

    private AccountApiResponse accountApiResponse;
    private CardInfoApiResponse cardInfoApiResponse;
    private CustomerApiResponse customerApiResponse;
    private AddressApiResponse addressApiResponse;
    private DeliveryApiResponse deliveryApiResponse;
    private ProductApiResponse productApiResponse;

}
