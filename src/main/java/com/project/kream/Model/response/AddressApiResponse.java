package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.AddressFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressApiResponse {
    private Long id;
    private String name;
    private String hp;
    private String zipcode;
    private String detail1;
    private String detail2;
    private Long customerId;
    private AddressFlag flag;
    private LocalDateTime regdate;
    private CustomerApiResponse customerApiResponse;
}
