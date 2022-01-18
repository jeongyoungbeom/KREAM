package com.project.kream.Model.response;

import com.project.kream.Model.Entity.ProSize;
import lombok.*;

@Getter
public class ProsizeApiResponse {
    private Long id;
    private Long productId;
    private String sizeType;

    public ProsizeApiResponse(ProSize proSize) {
        this.id = proSize.getId();
        this.productId = proSize.getProduct().getId();
        this.sizeType = proSize.getSizeType();
    }
}
