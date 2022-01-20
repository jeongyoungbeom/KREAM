package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Account;
import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.SalesStatus2;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class SalesAdminApiResponse {
    private String userid;
    private String email;
    private LocalDateTime regdate;
    private String productName;
    private Long productId;
    private String brand;
    private String size;
    private Long price;
    private SalesStatus2 status2;
    private String bank;
    private String accountNumber;
    private String name;

    public SalesAdminApiResponse(Customer customer, Sales sales, Product product, Account account) {
        this.userid = customer.getUserid();
        this.email = customer.getEmail();
        this.regdate = sales.getRegdate();
        this.productName = product.getName();
        this.productId = product.getId();
        this.size = sales.getSizeType();
        this.price = sales.getPrice();
        this.status2 = sales.getStatus2();
        this.bank = account.getBank();
        this.accountNumber = account.getAccountNumber();
        this.name = account.getName();
    }
}
