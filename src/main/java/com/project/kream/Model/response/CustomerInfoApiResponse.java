package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
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

    public CustomerInfoApiResponse(Customer customer, String Bank, String AccountNumber, String Name, List<CustomerAddressApiResponse> customerAddressApiResponseList, List<CustomerCardInfoApiResponse> customerCardInfoApiResponseList) {
        this.userid = customer.getEmail();
        this.userpw = customer.getUserpw();
        this.name = customer.getUserid();
        this.hp = customer.getHp();
        this.shoesize = customer.getShoesize();
        this.bank = Bank;
        this.accountNumber = AccountNumber;
        this.accountname = Name;
        this.agreement = customer.getAgreement();
        this.smsAllow = customer.getSmsAllow();
        this.emailAllow = customer.getEmailAllow();
        this.privacyPolicy = customer.getPrivacyPolicy();
        this.message = customer.getMessage();
        this.point = customer.getPoint();
        this.rank = customer.getRank();
        this.customerAddressApiResponseList = customerAddressApiResponseList;
        this.customerCardInfoApiResponseList = customerCardInfoApiResponseList;
    }
}
