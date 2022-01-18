package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.ProductQna;
import com.project.kream.Model.enumclass.ProductQnaType;
import com.project.kream.Model.enumclass.QnaStauts;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ProductQnaApiRequest {
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

    public ProductQna toEntity(Customer customer){
        return ProductQna.builder()
                .status(status)
                .type(type)
                .title(title)
                .content(content)
                .customer(customer)
                .build();
    }
}
