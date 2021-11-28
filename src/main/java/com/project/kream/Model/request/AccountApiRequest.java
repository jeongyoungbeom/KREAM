package com.project.kream.Model.request;

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
}
