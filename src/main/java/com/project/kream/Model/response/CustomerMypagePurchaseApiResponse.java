package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.*;

@Getter
public class CustomerMypagePurchaseApiResponse {
    private Long id;
    private String name;
    private String size;
    private String originFileName;
    private PurchaseStatus1 status1;
    private PurchaseStatus2 status2;
    private PurchaseStatus3 status3;

    public CustomerMypagePurchaseApiResponse(Product product, Purchase purchase) {
        this.id = purchase.getId();
        this.name = product.getName();
        this.size = purchase.getSizeType();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.status1 = purchase.getStatus1();
        this.status2 = purchase.getStatus2();
        this.status3 = purchase.getStatus3();
    }
}
