package com.project.kream.Model.response;

import com.project.kream.Model.Entity.ProductQna;
import com.project.kream.Model.enumclass.ProductQnaType;
import com.project.kream.Model.enumclass.QnaStauts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductQnaApiResponse {
    private Long id;
    private QnaStauts status;
    private ProductQnaType type;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private LocalDateTime modifiedDate;
    private String answer;
    private String acomment;
    private Long customerId;

    public ProductQnaApiResponse(ProductQna productQna) {
        this.status = productQna.getStatus();
        this.type = productQna.getType();
        this.title = productQna.getTitle();
        this.content = productQna.getContent();
        this.regdate = productQna.getRegdate();
        this.modifiedDate = productQna.getIsmodified();
        this.answer = productQna.getAnswer();
        this.acomment = productQna.getAcomment();
        this.customerId = productQna.getCustomer().getId();
    }
}
