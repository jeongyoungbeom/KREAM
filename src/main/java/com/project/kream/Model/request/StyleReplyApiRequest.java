package com.project.kream.Model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleReplyApiRequest {
    private Long id;
    private Long styleId;;
    private Long customerId;
    private String content;
    private Long hit;
    private Long parentId;
    private Long depth;
    private Long groupNum;
    private Long groupId;
    private LocalDateTime regdate;
}
