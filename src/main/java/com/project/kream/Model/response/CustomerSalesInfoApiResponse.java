package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSalesInfoApiResponse {
    private Long id;
    private Long productId;
    private String name;
    private String originFileName;
    private String size;
    private Long price;
    private Long period;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
    private LocalDateTime regdate;

    public CustomerSalesInfoApiResponse(Product product, Sales sales) {
        this.id = sales.getId();
        this.productId = product.getId();
        this.name = product.getName();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.size = sales.getSizeType();
        this.price = sales.getPrice();
        this.period = sales.getPeriod();
        this.status1 = sales.getStatus1();
        this.status2 = sales.getStatus2();
        this.status3 = sales.getStatus3();
        this.regdate = sales.getRegdate();
    }
}
