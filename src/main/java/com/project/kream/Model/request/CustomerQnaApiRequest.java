package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
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
public class CustomerQnaApiRequest {
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

    public CustomerQna toEntity(Customer customer){
        return CustomerQna.builder()
                .status(status)
                .type(type)
                .title(title)
                .content(content)
                .answer(answer)
                .customer(customer)
                .build();
    }
}
