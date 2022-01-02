package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Account;
import com.project.kream.Model.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountApiRequest {
    private Long id;
    private String bank;
    private String accountNumber;
    private String name;
    private Long customerId;

    public Account toEntity(Customer customer) {
        return Account.builder()
                .bank(bank)
                .accountNumber(accountNumber)
                .name(name)
                .customer(customer)
                .build();
    }
}
