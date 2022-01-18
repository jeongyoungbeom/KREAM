package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
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

    public CustomerApiResponse(Customer customer) {
        this.email = customer.getEmail();
        this.userid = customer.getUserid();
        this.userpw = customer.getUserpw();
        this.hp = customer.getHp();
        this.shoesize = customer.getShoesize();
        this.agreement = customer.getAgreement();
        this.privacyPolicy = customer.getPrivacyPolicy();
        this.smsAllow = customer.getSmsAllow();
        this.emailAllow = customer.getEmailAllow();
        this.image = customer.getImage();
        this.point = customer.getPoint();
        this.message = customer.getMessage();
        this.regdate = customer.getRegdate();
        this.rank = customer.getRank();
        this.type = customer.getType();
        this.role = customer.getRole();
    }
}
