package com.project.kream.Model.response;

import com.project.kream.Model.Entity.CardInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCardInfoApiResponse {
    private String cardCompany;
    private String cardNumber;

    public CustomerCardInfoApiResponse(CardInfo cardInfo) {
        this.cardCompany = cardInfo.getCardCompany();
        this.cardNumber = cardInfo.getCardNumber();
    }
}
