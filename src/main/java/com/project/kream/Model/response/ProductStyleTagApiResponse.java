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

}
