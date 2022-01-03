package com.project.kream.Model.response;

import com.project.kream.Model.Entity.CustomerQna;
import com.project.kream.Model.enumclass.CustomerQnaType;
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
public class CustomerQnaApiResponse {
    private Long id;
    private QnaStauts status;
    private CustomerQnaType type;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private LocalDateTime modifiedDate;
    private String answer;
    private String acomment;
    private Long customerId;

    public CustomerQnaApiResponse(CustomerQna customerQna) {
        this.id = customerQna.getId();
        this.status = customerQna.getStatus();
        this.type = customerQna.getType();
        this.title = customerQna.getTitle();
        this.content = customerQna.getContent();
        this.regdate = customerQna.getRegdate();
        this.modifiedDate = customerQna.getIsmodified();
        this.answer = customerQna.getAnswer();
        this.acomment = customerQna.getAcomment();
        this.customerId = customerQna.getCustomer().getId();
    }
}
