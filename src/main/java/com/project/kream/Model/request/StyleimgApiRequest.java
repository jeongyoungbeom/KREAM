package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleimgApiRequest {
    private Long id;
    private String origFileName;
    private String filePath;
    private Long fileSize;
    private Long styleId;

    public StyleImg toEntity(Style style){
        return StyleImg.builder()
                .origFileName(origFileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .style(style)
                .build();
    }

}
