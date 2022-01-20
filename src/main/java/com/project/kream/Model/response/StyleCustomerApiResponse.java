package com.project.kream.Model.response;

import com.project.kream.Model.Entity.StyleCustomer;
import lombok.*;

@Getter
public class StyleCustomerApiResponse {
    private Long id;
    private Long customerId;
    private String profileName;
    private String name;
    private String intro;

    public StyleCustomerApiResponse(StyleCustomer styleCustomer) {
        this.id = styleCustomer.getId();
        this.customerId = styleCustomer.getCustomer().getId();
        this.profileName = styleCustomer.getProfileName();
        this.name = styleCustomer.getName();
        this.intro = styleCustomer.getIntro();
    }
}
