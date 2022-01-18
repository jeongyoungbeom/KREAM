package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.ProductQnaType;
import com.project.kream.Model.enumclass.QnaStauts;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name="seq_product_qna",
        sequenceName = "seq_product_qna",
        initialValue = 1,
        allocationSize = 1
)
public class ProductQna extends UpdateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_qna")
    private Long id;

    @Enumerated(EnumType.STRING)
    private QnaStauts status;

    @Enumerated(EnumType.STRING)
    private ProductQnaType type;
    private String title;
    private String content;
    private String answer;
    private String acomment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public void update(QnaStauts status, ProductQnaType type, String title, String content, String answer, String acomment){
        this.status = status;
        this.type = type;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.acomment = acomment;
    }
}

