package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Style;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class StyleApiResponse {
    private Long id;
    private Long customerId;
    private String content;
    private Long hit;
    private LocalDateTime regdate;
    private Long replcnt;
    private Long imgcnt;
    private List<StyleReplyApiResponse> styleReplyList;
    private List<ProductTagApiResponse> productTagList;
    private List<HashTagApiResponse> hashTagList;
    private List<StyleimgApiResponse> styleimgList;

    public StyleApiResponse(Style style) {
        this.id = style.getId();
        this.customerId = style.getCustomer().getId();
        this.content = style.getContent();
    }
}
