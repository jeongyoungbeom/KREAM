package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountApiResponse {
    private Long id;
    private String bank;
    private String accountNumber;
    private String name;
    private Long customerId;

    public AccountApiResponse(Account account) {
        this.id = account.getId();
        this.bank = account.getBank();
        this.accountNumber = account.getAccountNumber();
        this.name = account.getName();
        this.customerId = account.getCustomer().getId();
    }
}
