package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleLikeApiRequest {
    private Long customerId;
    private Long styleId;

    public StyleLike toEntity(Customer customer, Style style){
        return StyleLike.builder()
                .customer(customer)
                .style(style)
                .build();
    }
}
