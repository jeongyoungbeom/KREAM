package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.SalesStatus2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesAdminApiResponse {
    private String userid;
    private String email;
    private LocalDateTime regdate;
    private String productName;
    private Long productId;
    private String brand;
    private String size;
    private Long price;
    private SalesStatus2 status2;
    private String bank;
    private String accountNumber;
    private String name;
}
