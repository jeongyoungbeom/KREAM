package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Address;
import com.project.kream.Model.enumclass.AddressFlag;
import lombok.*;

@Getter
public class ProductAddressApiResponse {
    private String name;
    private String hp;
    private String zipcode;
    private String address1;
    private String address2;
    private Long addressId;
    private AddressFlag addressFlag;

    public ProductAddressApiResponse(Address address) {
        this.name = address.getName();
        this.hp = address.getHp();
        this.zipcode = address.getZipcode();
        this.address1 = address.getDetail1();
        this.address2 = address.getDetail2();
        this.addressId = address.getId();
        this.addressFlag = address.getFlag();
    }
}
