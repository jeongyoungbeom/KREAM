package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSalesInfoApiResponse {
    private Long id;
    private Long productId;
    private String name;
    private String originFileName;
    private String size;
    private Long price;
    private Long period;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
    private LocalDateTime regdate;
}
