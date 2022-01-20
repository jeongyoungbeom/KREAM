package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.StyleCustomer;
import lombok.*;

@Getter
public class StyleCustomerApiRequest {
    private Long id;
    private Long customerId;
    private String profileName;
    private String name;
    private String intro;

    public StyleCustomer toEntity(Customer customer, String generatedString, String name){
        return StyleCustomer.builder()
                .customer(customer)
                .profileName(generatedString)
                .name(name)
                .build();
    }
}
