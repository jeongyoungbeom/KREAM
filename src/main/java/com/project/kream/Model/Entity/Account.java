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
        name="seq_account",
        sequenceName = "seq_account",
        initialValue = 1,
        allocationSize = 1
)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account")
    private Long id;
    private String bank;
    private String accountNumber;
    private String name;

    @OneToOne
    private Customer customer;

}
