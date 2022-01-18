package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleCustomer;
import lombok.*;

@Getter
public class StyleHashTagListApiResponse {
    private Long id;
    private String profileName;
    private String customerOriginFileName;
    private String styleOriginFileName;

    public StyleHashTagListApiResponse(Style style, StyleCustomer styleCustomer, Customer customer) {
        this.id = style.getId();
        this.profileName = styleCustomer.getProfileName();
        this.customerOriginFileName = customer.getImage();
        this.styleOriginFileName = style.getStyleImgList().get(0).getOrigFileName();
    }
}
