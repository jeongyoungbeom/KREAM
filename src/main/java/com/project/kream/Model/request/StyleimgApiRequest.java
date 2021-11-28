package com.project.kream.Model.request;

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

}
