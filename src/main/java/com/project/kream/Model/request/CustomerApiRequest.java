package com.project.kream.Model.request;

import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerApiRequest {
    private Long id;
    private String email;
    private String userid;
    private String userpw;
    private String newuserpw;
    private String hp;
    private String shoesize;
    private String agreement;
    private String privacyPolicy;
    private String smsAllow;
    private String emailAllow;
    private String image;
    private Long point;
    private String message;
    private CustomerRank rank;
    private CustomerType type;
    private CustomerRole role;
    private Long regdate;
    private String startDatetime;
    private String endDatetime;
}
