package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Address;
import com.project.kream.Model.Entity.Customer;
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

    public Address toEntity(Customer customer){
        return Address.builder()
                .name(name)
                .hp(hp)
                .zipcode(zipcode)
                .detail1(detail1)
                .detail2(detail2)
                .flag(flag)
                .customer(customer)
                .build();
    }

}
