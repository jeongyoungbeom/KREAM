package com.project.kream.Model.response;

import com.project.kream.Model.Entity.CardInfo;
import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.enumclass.CardFlag;
import lombok.*;

@Getter
public class CardInfoApiResponse {
    private Long id;
    private CardFlag cardFlag;
    private String cardCompany;
    private String cardNumber;
    private String expiration;
    private String birthdate;
    private String cardpw;
    private Long customerId;

    public CardInfoApiResponse(CardInfo cardInfo){
        this.id = cardInfo.getId();
        this.cardCompany = cardInfo.getCardCompany();
        this.cardNumber = cardInfo.getCardNumber();
        this.expiration = cardInfo.getExpiration();
        this.birthdate = cardInfo.getBirthdate();
        this.cardpw = cardInfo.getCardpw();
        this.cardFlag = cardInfo.getCardFlag();
        this.customerId = cardInfo.getCustomer().getId();
    }
}
