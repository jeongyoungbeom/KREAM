package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerMypageSalesApiResponse {
    private Long id;
    private String name;
    private String size;
    private String originFileName;
    private SalesStatus1 status1;
    private SalesStatus2 status2;
    private SalesStatus3 status3;
}
