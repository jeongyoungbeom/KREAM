package com.project.kream.Model.request;


import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.ProductTag;
import com.project.kream.Model.Entity.Style;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTagApiRequest {
    private Long id;
    private Long styleId;;
    private Long productId;

    public ProductTag toEntity(Style style, Product product){
        return ProductTag.builder()
                .style(style)
                .product(product)
                .build();
    }
}
