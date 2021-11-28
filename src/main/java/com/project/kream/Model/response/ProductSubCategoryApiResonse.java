package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductSubCategoryApiResonse {
    private Long id;
    private String oringinFileName;
    private String brand;
    private String name;
    private Long price;
}
