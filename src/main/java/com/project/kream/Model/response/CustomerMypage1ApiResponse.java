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
public class CustomerMypage1ApiResponse {
    private String userid;
    private String email;
    private CustomerRank rank;
    private Long point;
    private String originFileName;
    private List<CustomerMypagePurchaseApiResponse> customerMypagePurchaseApiResponseList;
    private List<CustomerMypageSalesApiResponse> customerMypageSalesApiResponseList;
    private List<CustomerMypageCartApiResponse> customerMypageCartApiResponseList;

}
