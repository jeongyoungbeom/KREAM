package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleCustomer;
import lombok.*;

import java.util.List;

@Getter
public class ProductStyleTagApiResponse {
    private Long styleId;
    private String userId;
    private String userImg;
    private String styleImg;
    private String content;
    private Long imgCnt;
    private Long replyCnt;
    private Long hit;
    private List<ProductHashtagApiResponse> productHashtagApiResponseList;

    public ProductStyleTagApiResponse(Style style, StyleCustomer styleCustomer, Customer customer, Long imgCnt, Long replyCnt, List<ProductHashtagApiResponse> productHashtagApiResponseList) {
        this.styleId = style.getId();
        this.userId = styleCustomer.getProfileName();
        this.userImg = customer.getImage();
        this.styleImg = style.getStyleImgList().get(0).getOrigFileName();
        this.content = style.getContent();
        this.imgCnt = imgCnt;
        this.replyCnt = replyCnt;
        this.hit = style.getHit();
        this.productHashtagApiResponseList = productHashtagApiResponseList;
    }
}
