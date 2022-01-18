package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Account;
import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.util.List;

@Getter
public class ProductSellFinalResponse {
    private Long id;
    private String name;
    private String korName;
    private String originFileName;
    private String size;
    private String modelNumber;
    private String accountName;
    private Long accountId;
    private Long price;
    private String bank;
    private String accountNumber;
    private List<ProductAddressApiResponse> productAddressApiResponseList;

    public ProductSellFinalResponse(Product product, Account account, String size, Long price, List<ProductAddressApiResponse> productAddressApiResponseList) {
        this.id = id;
        this.name = name;
        this.korName = korName;
        this.originFileName = originFileName;
        this.size = size;
        this.modelNumber = modelNumber;
        this.accountName = accountName;
        this.accountId = accountId;
        this.price = price;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.productAddressApiResponseList = productAddressApiResponseList;
    }
}
