package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleReply;
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

    public StyleReply toEntity(Style style, Customer customer,Long parentId, Long depth, Long groupNum,  Long groupId){
        return StyleReply.builder()
                .style(style)
                .customer(customer)
                .content(content)
                .hit(0L)
                .parentId(parentId)
                .depth(depth)
                .groupNum(groupNum)
                .groupId(groupId)
                .build();
    }

}
