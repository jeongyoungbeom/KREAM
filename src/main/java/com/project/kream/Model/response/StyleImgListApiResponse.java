package com.project.kream.Model.response;

import com.project.kream.Model.Entity.StyleImg;
import lombok.*;

@Getter
public class StyleImgListApiResponse {
    private String origFileName;

    public StyleImgListApiResponse(StyleImg styleImg) {
        this.origFileName = styleImg.getOrigFileName();
    }
}
