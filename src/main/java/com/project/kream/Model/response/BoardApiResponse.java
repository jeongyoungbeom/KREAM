package com.project.kream.Model.response;

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
public class BoardApiResponse {
    private Long id;
    private String title;
    private String content;
    private String registrant;
    private BoardCategory category;
    private LocalDateTime regdate;
}
