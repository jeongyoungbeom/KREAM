package com.project.kream.Model.response;

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
public class BoardSearchApiResponse {
    private Long id;
    private BoardCategory boardCategory;
    private String title;
    private LocalDateTime regdate;

    public BoardSearchApiResponse(Board board){
        this.id = board.getId();
        this.boardCategory = board.getCategory();
        this.title = board.getTitle();
        this.regdate = board.getRegdate();
    }
}
