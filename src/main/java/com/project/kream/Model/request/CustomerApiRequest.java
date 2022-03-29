package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import lombok.*;

@Getter
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

    public Customer toEntity(String userpw) {
        return Customer.builder()
                .email(email)
                .userid(userid)
                .userpw(userpw)
                .hp(hp)
                .shoesize(shoesize)
                .agreement(agreement)
                .privacyPolicy(privacyPolicy)
                .smsAllow(smsAllow)
                .emailAllow(emailAllow)
                .image(image)
                .point(point)
                .message(message)
                .rank(CustomerRank.BRONZE)
                .type(CustomerType.일반)
                .role(CustomerRole.USER)
                .build();
    }
}
