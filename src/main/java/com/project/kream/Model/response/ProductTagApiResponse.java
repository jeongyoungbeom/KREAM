package com.project.kream.Model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductTagApiResponse {
    private Long id;
    private Long styleId;;
    private Long productId;
    private String originFileName;
    private Long releasePrice;
    private String name;

}
