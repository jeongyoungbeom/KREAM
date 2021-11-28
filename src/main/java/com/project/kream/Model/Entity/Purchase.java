package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name="seq_purchase",
        sequenceName = "seq_purchase",
        initialValue = 1,
        allocationSize = 1
)
public class Purchase extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_purchase")
    private Long id;
    private Long price;
    private Long period;
    private String sizeType;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus1 status1;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus2 status2;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus3 status3;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @OneToOne(mappedBy = "purchase")
    private Delivery delivery;

    @OneToOne
    private Address address;

    @OneToOne
    private CardInfo cardInfo;

    @OneToOne
    private Sales sales;
}
