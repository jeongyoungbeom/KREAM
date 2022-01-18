package com.project.kream.Model.response;

import com.project.kream.Model.Entity.ProImg;
import lombok.*;

@Getter
public class ProductImgApiResponse {
    private String originFileName;

    public ProductImgApiResponse(ProImg proImg) {
        this.originFileName = proImg.getOrigFileName();
    }
}
