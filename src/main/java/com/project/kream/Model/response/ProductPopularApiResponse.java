package com.project.kream.Model.response;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductPopularApiResponse {
    private Long id;
    private String originFileName;
    private String brand;
    private String name;
    private Long price;
    private Long amount;
}
