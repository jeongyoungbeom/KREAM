package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleCustomer;
import lombok.*;

import java.util.List;

@Getter
public class StyleHitListApiResponse {
    private Long styleId;
    private String userid;
    private String userImg;
    private Long styleCnt;
    private String content;
    private List<StyleProductTagApiResponse> styleProductTagApiResponseList;
    private List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList;
    private String styleImg;
    private Long hit;
    private Long replyCnt;
    private boolean hitBoolean;


    public StyleHitListApiResponse(Style style, StyleCustomer styleCustomer, Customer customer, Long styleCnt, List<StyleProductTagApiResponse> styleProductTagApiResponseList, List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList, Long replyCnt, boolean hitBoolean) {
        this.styleId = style.getId();
        this.userid = styleCustomer.getProfileName();
        this.userImg = customer.getImage();
        this.styleCnt = styleCnt;
        this.content = style.getContent();
        this.styleProductTagApiResponseList = styleProductTagApiResponseList;
        this.styleHashTagNameApiResponseList = styleHashTagNameApiResponseList;
        this.styleImg = style.getStyleImgList().get(0).getOrigFileName();
        this.hit =style.getHit();
        this.replyCnt = replyCnt;
        this.hitBoolean = hitBoolean;
    }
}
