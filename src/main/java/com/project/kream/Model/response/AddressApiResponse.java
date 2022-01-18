package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Address;
import com.project.kream.Model.Entity.Customer;
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

    public AddressApiResponse(Address address) {
        this.id = address.getId();
        this.name = address.getName();
        this.hp = address.getHp();
        this.zipcode = address.getZipcode();
        this.detail1 = address.getDetail1();
        this.detail2 = address.getDetail2();
        this.customerId = address.getCustomer().getId();
        this.flag = address.getFlag();
        this.regdate = address.getRegdate();
    }
    //
}
