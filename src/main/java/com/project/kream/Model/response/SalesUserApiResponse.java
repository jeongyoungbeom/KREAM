package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Account;
import com.project.kream.Model.Entity.Address;
import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SalesUserApiResponse {
    private Long orderNumber;
    private Long period;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private Long productId;
    private String productName;
    private String size;
    private String originFileName;
    private Long price;
    private LocalDateTime regdate;
    private String bank;
    private String accountNumber;
    private String cardCompany;
    private String cardNumber;
    private String name;
    private String hp;
    private String zipcode;
    private String address1;
    private String address2;
    private Long salesPrice;
    private Long purchasePrice;

    public SalesUserApiResponse(Sales sales, Product product, Account account, Address address, Long salesPrice, Long purchasePrice) {
        this.orderNumber = sales.getId();
        this.period = sales.getPeriod();
        this.status1 = sales.getStatus1();
        this.status2 = sales.getStatus2();
        this.productId = product.getId();
        this.productName = product.getName();
        this.size = sales.getSizeType();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.price = sales.getPrice();
        this.regdate = sales.getRegdate();
        this.bank = account.getBank();
        this.accountNumber = account.getAccountNumber();
        this.name = address.getName();
        this.hp = address.getHp();
        this.zipcode = address.getZipcode();
        this.address1 = address.getDetail1();
        this.address2 = address.getDetail2();
        this.salesPrice = salesPrice;
        this.purchasePrice = purchasePrice;
    }
}
