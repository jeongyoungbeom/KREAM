package com.project.kream.Model.response;

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
}
