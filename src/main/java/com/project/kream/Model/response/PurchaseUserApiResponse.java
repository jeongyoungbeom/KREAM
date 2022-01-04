package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseUserApiResponse {
    private Long orderNumber;
    private Long period;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private Long productId;
    private String productName;
    private String size;
    private String originFileName;
    private Long price;
    private LocalDateTime regdate;
    private String name;
    private String hp;
    private String zipcode;
    private String address1;
    private String address2;
    private String cardCompany;
    private String cardNumber;
    private String devCompany;
    private Long trackNum;
    private Long salesPrice;
    private Long purchasePrice;

    public PurchaseUserApiResponse(Purchase purchase, String devCompany, Long trackNum, Long salesPrice, Long purchasePrice) {
        this.orderNumber = purchase.getId();
        this.period = purchase.getPeriod();
        this.status1 = purchase.getStatus1();
        this.status2 = purchase.getStatus2();
        this.productId = purchase.getProduct().getId();
        this.productName = purchase.getProduct().getName();
        this.size = purchase.getSizeType();
        this.originFileName = purchase.getProduct().getProImgList().get(0).getOrigFileName();
        this.price = purchase.getPrice();
        this.regdate = purchase.getRegdate();
        this.name = purchase.getAddress().getName();
        this.hp = purchase.getAddress().getHp();
        this.zipcode = purchase.getAddress().getZipcode();
        this.address1 = purchase.getAddress().getDetail1();
        this.address2 = purchase.getAddress().getDetail2();
        this.cardCompany = purchase.getCardInfo().getCardCompany();
        this.cardNumber = purchase.getCardInfo().getCardNumber();
        this.devCompany = devCompany;
        this.trackNum = trackNum;
        this.salesPrice = salesPrice;
        this.purchasePrice = purchasePrice;
    }
}
