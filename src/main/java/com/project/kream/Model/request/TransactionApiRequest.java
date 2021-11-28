package com.project.kream.Model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionApiRequest {
    private String sizeType;
    private Long productId;
    private Long price;
}
