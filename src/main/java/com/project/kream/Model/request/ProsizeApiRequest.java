package com.project.kream.Model.request;

import com.project.kream.Model.Entity.ProSize;
import com.project.kream.Model.Entity.Product;
import lombok.*;

@Getter
public class ProsizeApiRequest {
    private Long id;
    private Long productId;
    private String sizeType;

    public ProSize toEntity(Product product){
        return ProSize.builder()
                .sizeType(sizeType)
                .product(product)
                .build();
    }
}
