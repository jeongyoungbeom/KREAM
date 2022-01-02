package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name="seq_sales",
        sequenceName = "seq_sales",
        initialValue = 1,
        allocationSize = 1
)

public class Sales extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sales")
    private Long id;
    private Long price;
    private Long period;
    private String sizeType;

    @Enumerated(EnumType.STRING)
    private SalesStatus1 status1;

    @Enumerated(EnumType.STRING)
    private SalesStatus2 status2;

    @Enumerated(EnumType.STRING)
    private SalesStatus3 status3;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    private Purchase purchase;


}
