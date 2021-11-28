package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPopularApiResponse {
    private Long id;
    private String originFileName;
    private String brand;
    private String name;
    private Long price;
    private Long amount;
}
