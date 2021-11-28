package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleDetailProductTagApiResponse {
    private Long id;
    private String origFileName;
    private String name;
    private Long price;
}
