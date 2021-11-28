package com.project.kream.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@SequenceGenerator(
        name="seq_styleimg",
        sequenceName = "seq_styleimg",
        initialValue = 1,
        allocationSize = 1
)
public class StyleImg {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_styleimg")
    private Long id;
    private String origFileName;
    private String filePath;
    private Long fileSize;

    @ManyToOne
    private Style style;
}
