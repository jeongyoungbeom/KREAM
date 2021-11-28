package com.project.kream.Model.response;

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
}
