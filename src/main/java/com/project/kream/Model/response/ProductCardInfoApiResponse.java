package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.CardFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductCardInfoApiResponse {
    private String cardCompany;
    private String cardNumber;
    private CardFlag cardFlag;
    private Long id;
}
