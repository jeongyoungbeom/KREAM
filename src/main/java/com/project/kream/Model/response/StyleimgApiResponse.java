package com.project.kream.Model.response;

import com.project.kream.Model.Entity.StyleImg;
import lombok.*;

@Getter
public class StyleimgApiResponse {
    private Long id;
    private String origFileName;
    private String filePath;
    private Long fileSize;
    private Long styleId;


    public StyleimgApiResponse(StyleImg styleImg) {
        this.id = styleImg.getId();
        this.origFileName = styleImg.getOrigFileName();
        this.filePath = styleImg.getFilePath();
        this.fileSize = styleImg.getFileSize();
        this.styleId = styleImg.getStyle().getId();
    }
}
