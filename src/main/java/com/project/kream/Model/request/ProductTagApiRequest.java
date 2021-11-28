package com.project.kream.Model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductTagApiRequest {
    private Long id;
    private Long styleId;;
    private Long productId;
}
