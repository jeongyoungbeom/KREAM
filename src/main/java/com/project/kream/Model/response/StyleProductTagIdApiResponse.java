package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleProductTagIdApiResponse {
    private Long productId;
    private String originFileName;
    private String name;
}
