package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPostCntApiResponse {
    private Long allCnt;
    private Long waitCnt;
    private Long ingCnt;
    private Long stopCnt;
    private Long finishCnt;
}
