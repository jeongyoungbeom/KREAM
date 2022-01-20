package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.ReplyLike;
import com.project.kream.Model.Entity.StyleReply;
import lombok.*;

@Getter
public class ReplyLikeApiRequest {
    private Long customerId;
    private Long replyId;

    public ReplyLike toEntity(Customer customer, StyleReply styleReply){
        return ReplyLike.builder()
                .customer(customer)
                .styleReply(styleReply)
                .build();
    }
}
