package com.project.kream.Model.Entity;

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

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;
}
