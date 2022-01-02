package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.BoardCategory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_board",
        sequenceName = "seq_board",
        initialValue = 1,
        allocationSize = 1
)
public class Board extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_board")
    private Long id;
    private String title;
    private String content;
    private String registrant;

    @Enumerated(EnumType.STRING)
    private BoardCategory category;

    @Builder
    public Board(String title, String content, String registrant, BoardCategory category) {
        this.title = title;
        this.content = content;
        this.registrant = registrant;
        this.category = category;
    }

    public void update(String title, String content, String registrant, BoardCategory category){
        this.title = title;
        this.content = content;
        this.registrant = registrant;
        this.category = category;
    }
}
