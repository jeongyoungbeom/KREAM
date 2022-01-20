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
@AllArgsConstructor
@Builder
public class StyleUserListApiResponse {
    private Long styleId;
    private String content;
    private List<StyleProductTagApiResponse> styleProductTagApiResponseList;
    private List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList;
    private String styleImg;
    private Long hit;
    private Long replyCnt;
    private Long styleCnt;
    private boolean hitBoolean;
    private LocalDateTime regdate;

    public StyleUserListApiResponse(Style style, List<StyleProductTagApiResponse> styleProductTagApiResponseList, List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList, Long replyCnt, Long styleCnt, boolean hitBoolean) {
        this.styleId = style.getId();
        this.content = style.getContent();
        this.styleProductTagApiResponseList = styleProductTagApiResponseList;
        this.styleHashTagNameApiResponseList = styleHashTagNameApiResponseList;
        this.styleImg = style.getStyleImgList().get(0).getOrigFileName();
        this.hit = style.getHit();
        this.replyCnt = replyCnt;
        this.styleCnt = styleCnt;
        this.hitBoolean = hitBoolean;
        this.regdate = style.getRegdate();
    }
}

