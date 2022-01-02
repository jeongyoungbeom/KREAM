package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Board;
import com.project.kream.Model.enumclass.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardApiRequest {
    private Long id;
    private String title;
    private String content;
    private String registrant;
    private BoardCategory category;
    private LocalDateTime regdate;
    private String startDatetime;
    private String endDatetime;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .registrant(registrant)
                .category(category)
                .build();
    }
}
