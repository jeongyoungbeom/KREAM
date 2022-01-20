package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.StyleCustomer;
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

    public StyleReplyDetailApiResponse(StyleReply styleReply, boolean replyBoolean) {
        this.id = styleReply.getId();
        this.customerId = styleReply.getCustomer().getId();
        this.customerUserid = styleReply.getCustomer().getStyleCustomerList().get(0).getProfileName();
        this.customerOriginFile = styleReply.getCustomer().getImage();
        this.content = styleReply.getContent();
        this.depth = styleReply.getDepth();
        this.hit = styleReply.getHit();
        this.replyBoolean = replyBoolean;
        this.regdate = styleReply.getRegdate();
    }
}


