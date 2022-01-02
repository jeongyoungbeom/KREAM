package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "purchase")
    private List<Delivery> deliveryList;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    private CardInfo cardInfo;

    @OneToOne(fetch = FetchType.LAZY)
    private Sales sales;
}
