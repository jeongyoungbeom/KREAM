package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleDetailListApiResponse {
    private Long styleId;
    private Long customerId;
    private String customerUserId;
    private String customerOriginFile;
    private Long hit;
    private String content;
    private List<StyleImgListApiResponse> styleImgListApiResponseList;
    private List<StyleDetailProductTagApiResponse> styleDetailProductTagApiResponseList;
    private List<StyleDetailHashTagApiResponse> styleDetailHashTagApiResponseList;
    private List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList;
    private boolean hitBoolean;
    private boolean followBoolean;
    private LocalDateTime regdate;

    public StyleDetailListApiResponse(Style style, List<StyleImgListApiResponse> styleImgListApiResponseList,List<StyleDetailProductTagApiResponse> styleDetailProductTagApiResponseList,List<StyleDetailHashTagApiResponse> styleDetailHashTagApiResponseList,  List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList , boolean hitBoolean, boolean followBoolean ) {
        this.styleId = style.getId();
        this.customerId = style.getCustomer().getId();
        this.customerUserId = style.getCustomer().getStyleCustomerList().get(0).getProfileName();
        this.customerOriginFile = style.getCustomer().getImage();
        this.hit = style.getHit();
        this.content = style.getContent();
        this.styleImgListApiResponseList = styleImgListApiResponseList;
        this.styleDetailProductTagApiResponseList = styleDetailProductTagApiResponseList;
        this.styleDetailHashTagApiResponseList = styleDetailHashTagApiResponseList;
        this.styleReplyDetailApiResponseList = styleReplyDetailApiResponseList;
        this.hitBoolean = hitBoolean;
        this.followBoolean = followBoolean;
        this.regdate = style.getRegdate();
    }
}
