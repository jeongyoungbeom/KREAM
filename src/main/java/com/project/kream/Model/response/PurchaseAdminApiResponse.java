package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.PurchaseStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseAdminApiResponse {
    private String userid;
    private String hp;
    private LocalDateTime regdate;
    private String productName;
    private Long productId;
    private String brand;
    private String size;
    private String email;
    private String cardNumber;
    private String cardCompany;
    private PurchaseStatus3 status3;
    private Long price;
}
