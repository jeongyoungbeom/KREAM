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
}
