package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionApiRequest {
    private String sizeType;
    private Long productId;
    private Long price;

    public Transaction toEntity(Product product){
        return Transaction.builder()
                .sizeType(sizeType)
                .product(product)
                .price(price)
                .build();
    }
}
