package com.project.kream.Model.request;

import com.project.kream.Model.enumclass.AddressFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressApiRequest {
    private Long id;
    private String name;
    private String hp;
    private String zipcode;
    private String detail1;
    private String detail2;
    private AddressFlag flag;
    private Long customerId;

}
