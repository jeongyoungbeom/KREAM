package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.CardFlag;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@SequenceGenerator(
        name="seq_card_info",
        sequenceName = "seq_card_info",
        initialValue = 1,
        allocationSize = 1
)
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_card_info")
    private Long id;
    private String cardCompany;
    private String cardNumber;
    private String expiration;
    private String birthdate;
    private String cardpw;

    @Enumerated(EnumType.STRING)
    private CardFlag cardFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Builder
    public CardInfo(String cardCompany, String cardNumber, String expiration, String birthdate, String cardpw, CardFlag cardFlag, Customer customer) {
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.birthdate = birthdate;
        this.cardpw = cardpw;
        this.cardFlag = cardFlag;
        this.customer = customer;
    }

    public void update(String cardCompany, String cardNumber, String expiration, String birthdate, String cardpw, CardFlag cardFlag, Customer customer){
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.birthdate = birthdate;
        this.cardpw = cardpw;
        this.cardFlag = cardFlag;
        this.customer = customer;
    }
}
