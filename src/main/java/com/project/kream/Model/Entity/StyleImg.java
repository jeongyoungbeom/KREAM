package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Style style;
}
