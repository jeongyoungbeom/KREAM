package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartApiResponse {
    private Long id;
    private String size;
    private Long productId;
    private Long customerId;
    private ProductApiResponse productApiResponse;
    private LocalDateTime regdate;
}
