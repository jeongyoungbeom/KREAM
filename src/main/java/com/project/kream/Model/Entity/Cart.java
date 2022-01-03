package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_cart",
        sequenceName = "seq_cart",
        initialValue = 1,
        allocationSize = 1
)
public class Cart extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cart")
    private Long id;
    private String sizeType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Cart(LocalDateTime regdate, String sizeType, Customer customer, Product product) {
        super(regdate);
        this.sizeType = sizeType;
        this.customer = customer;
        this.product = product;
    }
}
