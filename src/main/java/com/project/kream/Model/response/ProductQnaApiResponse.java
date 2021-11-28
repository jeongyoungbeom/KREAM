package com.project.kream.Model.response;

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
}
