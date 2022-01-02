package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
