package com.project.kream.Model.response;

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
public class StyleReplyPopApiResponse {
    private String styleCustomerId;
    private String content;
    private LocalDateTime regdate;
    private List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList;
    private String customerImg;
    private Long hit;
    private List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList;
}
