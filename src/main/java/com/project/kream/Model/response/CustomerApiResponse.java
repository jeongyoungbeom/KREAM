package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerApiResponse {
    private Long id;
    private String email;
    private String userid;
    private String userpw;
    private String hp;
    private String shoesize;
    private String agreement;
    private String privacyPolicy;
    private String smsAllow;
    private String emailAllow;
    private String image;
    private Long point;
    private String message;
    private LocalDateTime regdate;
    private CustomerRank rank;
    private CustomerType type;
    private CustomerRole role;
    private List<CartApiResponse> cartApiResponseList;
    private List<AddressApiResponse> addressApiResponseList;
    private AccountApiResponse accountApiResponse;
    private List<CardInfoApiResponse> cardInfoApiResponsesList;
    private List<PurchaseApiResponse> purchaseApiResponseList;
    private List<SalesApiResponse> salesApiResponseList;
}
