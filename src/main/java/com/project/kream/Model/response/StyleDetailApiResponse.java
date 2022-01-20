package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleDetailApiResponse {
    private Long id;
    private String content;
    private List<StyleImgListApiResponse> styleImgListApiResponseList;
    private List<StyleProductTagIdApiResponse> styleProductTagIdApiResponseList;
    private List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList;

    public StyleDetailApiResponse(Style style, List<StyleImgListApiResponse> styleImgListApiResponseList, List<StyleProductTagIdApiResponse> styleProductTagIdApiResponseList, List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList) {
        this.id = style.getId();
        this.content = style.getContent();
        this.styleImgListApiResponseList = styleImgListApiResponseList;
        this.styleProductTagIdApiResponseList = styleProductTagIdApiResponseList;
        this.styleHashTagNameApiResponseList = styleHashTagNameApiResponseList;
    }
}