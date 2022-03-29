package com.project.kream.Model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ProimgApiResponse {
    private Long id;
    private String origFileName;
    private String filePath;
    private Long fileSize;
    private Long productId;
    private LocalDateTime regdate;
}
