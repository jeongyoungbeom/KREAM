package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCollectionApiResponse {
    private Long id;
    private String oringinFileName;
    private String brand;
    private String name;
    private Long price;
}
