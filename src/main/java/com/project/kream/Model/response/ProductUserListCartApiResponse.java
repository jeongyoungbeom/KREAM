package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductUserListCartApiResponse {
    private Long id;
    private String korName;
    private String name;
    private String origFileName;
    private List<ProductSizeApiResponse> productSizeApiResponseList;
}
