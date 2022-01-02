package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Builder
    public Account(String bank, String accountNumber, String name, Customer customer) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.name = name;
        this.customer = customer;
    }

    public void update(String bank, String accountNumber, String name) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.name = name;
    }
}
