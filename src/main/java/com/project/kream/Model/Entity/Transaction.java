package com.project.kream.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@SequenceGenerator(
        name="seq_transaction",
        sequenceName = "seq_transaction",
        initialValue = 1,
        allocationSize = 1
)
public class Transaction extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction")
    private Long id;
    private String sizeType;
    private Long price;

    @ManyToOne
    private Product product;
}
