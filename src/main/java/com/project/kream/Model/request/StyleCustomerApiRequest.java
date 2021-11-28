package com.project.kream.Model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleCustomerApiRequest {
    private Long id;
    private Long customerId;
    private String profileName;
    private String name;
    private String intro;
}
