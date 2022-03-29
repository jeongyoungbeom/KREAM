package com.project.kream.Model.response;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
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
