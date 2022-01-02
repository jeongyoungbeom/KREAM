package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Cart;
import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerMypageCartApiResponse {
    private Long id;
    private Long productId;
    private String name;
    private String brand;
    private String originFileName;
    private Long price;

    public CustomerMypageCartApiResponse(Product product, Cart cart, Long price) {
        this.id = cart.getId();
        this.productId = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.originFileName = product.getProImgList().get(0).getOrigFileName();
        this.price = price;
    }
}
