package com.project.kream.Model.response;

import lombok.*;

@Getter
public class ProductPostCntApiResponse {
    private Long allCnt;
    private Long waitCnt;
    private Long ingCnt;
    private Long stopCnt;
    private Long finishCnt;

    public ProductPostCntApiResponse(Long allCnt, Long waitCnt, Long ingCnt, Long stopCnt, Long finishCnt) {
        this.allCnt = allCnt;
        this.waitCnt = waitCnt;
        this.ingCnt = ingCnt;
        this.stopCnt = stopCnt;
        this.finishCnt = finishCnt;
    }
}
