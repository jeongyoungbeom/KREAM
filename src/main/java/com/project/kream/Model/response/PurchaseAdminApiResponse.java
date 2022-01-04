package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseAdminApiResponse {
    private String userid;
    private LocalDateTime regdate;
    private String productName;
    private Long productId;
    private String brand;
    private String size;
    private String email;
    private String cardNumber;
    private String cardCompany;
    private PurchaseStatus3 status3;
    private Long price;

    public PurchaseAdminApiResponse(Purchase purchase) {
        this.userid = purchase.getCustomer().getUserid();
        this.regdate = purchase.getRegdate();
        this.productName = purchase.getProduct().getName();
        this.productId = purchase.getProduct().getId();
        this.brand = purchase.getProduct().getBrand();
        this.size = purchase.getSizeType();
        this.email = purchase.getCustomer().getEmail();
        this.cardNumber = purchase.getCardInfo().getCardNumber();
        this.cardCompany = purchase.getCardInfo().getCardCompany();
        this.status3 = purchase.getStatus3();
        this.price = purchase.getPrice();
    }
}
