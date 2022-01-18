package com.project.kream.Model.response;

import lombok.*;

@Getter
public class ProductSizeApiResponse {
        private String size;
        private Long cnt;

        public ProductSizeApiResponse(String size, Long cnt) {
                this.size = size;
                this.cnt = cnt;
        }
}
