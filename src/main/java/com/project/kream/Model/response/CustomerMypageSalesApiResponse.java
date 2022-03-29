package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.*;

@Getter
public class CustomerMypageSalesApiResponse {
    private Long id;
    private String name;
    private String size;
    private String originFileName;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private SalesStatus3 status3;

    public CustomerMypageSalesApiResponse(Product product, Sales sales) {
        this.id = sales.getId();
        this.name = product.getName();
        this.size = sales.getSizeType();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.status1 = sales.getStatus1();
        this.status2 = sales.getStatus2();
        this.status3 = sales.getStatus3();
    }
}
