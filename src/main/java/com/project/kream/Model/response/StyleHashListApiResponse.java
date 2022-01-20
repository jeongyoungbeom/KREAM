package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleCustomer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleHashListApiResponse {
    private String userid;
    private Long styleId;
    private String userImg;
    private Long styleCnt;
    private String content;
    private List<StyleProductTagApiResponse> styleProductTagApiResponseList;
    private List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList;
    private String styleImg;
    private Long hit;
    private Long replyCnt;
    private LocalDateTime regdate;
    private boolean hitBoolean;

    public StyleHashListApiResponse(StyleCustomer styleCustomer, Style style, Customer customer,Long styleCnt, List<StyleProductTagApiResponse> styleProductTagApiResponseList, List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList, Long replyCnt, boolean hitBoolean ) {
        this.userid = styleCustomer.getProfileName();
        this.styleId = style.getId();
        this.userImg = customer.getImage();
        this.styleCnt = styleCnt;
        this.content = style.getContent();
        this.styleProductTagApiResponseList = styleProductTagApiResponseList;
        this.styleHashTagNameApiResponseList = styleHashTagNameApiResponseList;
        this.styleImg = style.getStyleImgList().get(0).getOrigFileName();
        this.hit = style.getHit();
        this.replyCnt = replyCnt;
        this.regdate = style.getRegdate();
        this.hitBoolean = hitBoolean;
    }
}
