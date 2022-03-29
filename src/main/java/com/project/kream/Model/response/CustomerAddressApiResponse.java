package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Address;
import lombok.*;

@Getter
public class CustomerAddressApiResponse {
    private String address1;
    private String address2;
    private String zipcode;

    public CustomerAddressApiResponse(Address address) {
        this.address1 = address.getDetail1();
        this.address2 = address.getDetail2();
        this.zipcode = address.getZipcode();
    }
}
