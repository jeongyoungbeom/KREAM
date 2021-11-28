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
public class StyleReplyDetailApiResponse {
    private Long id;
    private Long customerId;
    private String customerUserid;
    private String customerOriginFile;
    private String content;
    private Long depth;
    private Long hit;
    private boolean replyBoolean;
    private LocalDateTime regdate;
}
