package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Cart;
import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartApiRequest {
    private Long id;
    private String sizeType;
    private Long productId;
    private Long customerId;
    private LocalDateTime regdate;

    public Cart toEntity(Product product, Customer customer){
        return Cart.builder()
                .sizeType(sizeType)
                .product(product)
                .customer(customer)
                .build();
    }
}
