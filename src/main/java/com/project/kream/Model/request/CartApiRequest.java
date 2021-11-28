package com.project.kream.Model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartApiRequest {
    private Long id;
    private String sizeType;
    private Long productId;
    private Long customerId;
    private LocalDateTime regdate;
}
