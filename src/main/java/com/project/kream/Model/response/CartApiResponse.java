package com.project.kream.Model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class CartApiResponse {
    private Long id;
    private String size;
    private Long productId;
    private Long customerId;
    private ProductApiResponse productApiResponse;
    private LocalDateTime regdate;
}
