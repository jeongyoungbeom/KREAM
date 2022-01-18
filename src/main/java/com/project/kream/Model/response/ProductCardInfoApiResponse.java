package com.project.kream.Model.response;

import com.project.kream.Model.Entity.CardInfo;
import com.project.kream.Model.enumclass.CardFlag;
import lombok.*;

@Getter
public class ProductCardInfoApiResponse {
    private String cardCompany;
    private String cardNumber;
    private CardFlag cardFlag;
    private Long id;

    public ProductCardInfoApiResponse(CardInfo cardInfo) {
        this.cardCompany = cardInfo.getCardCompany();
        this.cardNumber = cardInfo.getCardNumber();
        this.cardFlag = cardInfo.getCardFlag();
        this.id = cardInfo.getId();
    }
}
