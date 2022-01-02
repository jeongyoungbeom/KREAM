package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
