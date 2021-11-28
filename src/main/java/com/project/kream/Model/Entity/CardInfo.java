package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.CardFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @ManyToOne
    private Customer customer;
}
