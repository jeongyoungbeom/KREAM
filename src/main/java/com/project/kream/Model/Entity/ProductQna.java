package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.ProductQnaType;
import com.project.kream.Model.enumclass.QnaStauts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    private Customer customer;

}

