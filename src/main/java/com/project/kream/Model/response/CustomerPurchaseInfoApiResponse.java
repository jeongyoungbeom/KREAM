package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class CustomerPurchaseInfoApiResponse {
    private Long id;
    private Long productId;
    private String name;
    private String originFileName;
    private Long price;
    private String size;
    private Long period;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;
    private LocalDateTime regdate;

    public CustomerPurchaseInfoApiResponse(Product product, Purchase purchase) {
        this.id = purchase.getId();
        this.productId = product.getId();
        this.name = product.getName();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.price = purchase.getPrice();
        this.size = purchase.getSizeType();
        this.period = purchase.getPeriod();
        this.status1 = purchase.getStatus1();
        this.status2 = purchase.getStatus2();
        this.status3 = purchase.getStatus3();
        this.regdate = purchase.getRegdate();
    }
}
