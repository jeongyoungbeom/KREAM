package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Board;
import com.project.kream.Model.enumclass.BoardCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
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
