package com.project.kream.Model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleReplyApiResponse {
    private Long id;
    private Long styleId;
    private Long customerId;
    private String content;
    private Long hit;
    private Long parentId;
    private Long depth;
    private Long groupNum;
    private Long groupId;
    private LocalDateTime regdate;


}
