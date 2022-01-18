package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Transaction;
import lombok.*;

@Getter
public class ProductTopTransactionApiResponse {
    private Long price;

    public ProductTopTransactionApiResponse(Transaction transaction) {
        this.price = transaction.getPrice();
    }
}
