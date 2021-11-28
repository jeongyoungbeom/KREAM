package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.AddressFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductAddressApiResponse {
    private String name;
    private String hp;
    private String zipcode;
    private String address1;
    private String address2;
    private Long addressId;
    private AddressFlag addressFlag;
}
