package com.project.kream.Model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleApiRequest {
    private Long id;
    private Long customerId;
    private String content;
    private String[] tagName;
    private Long[] ProductId;
}
