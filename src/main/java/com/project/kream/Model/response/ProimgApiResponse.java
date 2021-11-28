package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProimgApiResponse {
    private Long id;
    private String origFileName;
    private String filePath;
    private Long fileSize;
    private Long productId;
    private LocalDateTime regdate;
}
