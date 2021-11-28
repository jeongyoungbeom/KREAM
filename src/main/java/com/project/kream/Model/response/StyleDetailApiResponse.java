package com.project.kream.Model.response;

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
}