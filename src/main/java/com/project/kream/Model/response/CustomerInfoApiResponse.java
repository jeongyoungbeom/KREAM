package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.CustomerRank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerInfoApiResponse {
    private String userid;
    private String userpw;
    private String name;
    private String hp;
    private String shoesize;
    private String bank;
    private String accountNumber;
    private String accountname;
    private String agreement;
    private String smsAllow;
    private String emailAllow;
    private String privacyPolicy;
    private String message;
    private Long point;
    private CustomerRank rank;
    private List<CustomerAddressApiResponse> customerAddressApiResponseList;
    private List<CustomerCardInfoApiResponse> customerCardInfoApiResponseList;
}
