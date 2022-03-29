package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Style;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class StyleReplyPopApiResponse {
    private String styleCustomerId;
    private String content;
    private LocalDateTime regdate;
    private List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList;
    private String customerImg;
    private Long hit;
    private List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList;

    public StyleReplyPopApiResponse(Style style, List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList, List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList) {
        this.styleCustomerId = style.getCustomer().getStyleCustomerList().get(0).getProfileName();
        this.content = style.getContent();
        this.regdate = style.getRegdate();
        this.styleHashTagNameApiResponseList = styleHashTagNameApiResponseList;
        this.customerImg = style.getCustomer().getImage();
        this.hit = style.getHit();
        this.styleReplyDetailApiResponseList = styleReplyDetailApiResponseList;
    }
}
