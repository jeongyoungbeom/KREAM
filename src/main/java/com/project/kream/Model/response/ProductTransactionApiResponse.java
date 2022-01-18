package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Transaction;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ProductTransactionApiResponse {
    private String sizeType;
    private Long price;
    private LocalDateTime regdate;

    public ProductTransactionApiResponse(Transaction transaction) {
        this.sizeType = transaction.getSizeType();
        this.price = transaction.getPrice();
        this.regdate = transaction.getRegdate();
    }
}
