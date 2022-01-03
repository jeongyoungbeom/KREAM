package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.CustomerQnaType;
import com.project.kream.Model.enumclass.QnaStauts;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@SequenceGenerator(
        name="seq_customer_qna",
        sequenceName = "seq_customer_qna",
        initialValue = 1,
        allocationSize = 1
)
public class CustomerQna extends UpdateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer_qna")
    private Long id;

    @Enumerated(EnumType.STRING)
    private QnaStauts status;

    @Enumerated(EnumType.STRING)
    private CustomerQnaType type;
    private String title;
    private String content;
    private String answer;
    private String acomment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Builder
    public CustomerQna(QnaStauts status, CustomerQnaType type, String title, String content, String answer,  Customer customer) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.customer = customer;
    }

    public void update(QnaStauts status, CustomerQnaType type, String title, String content, String answer, String acomment, Customer customer){
        this.status = status;
        this.type = type;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.acomment = acomment;
        this.customer = customer;
    }
}
