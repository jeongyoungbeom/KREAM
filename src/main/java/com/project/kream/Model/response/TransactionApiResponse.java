package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionApiResponse {
    private Long id;
    private String sizeType;
    private Long productId;
    private Long price;
}
