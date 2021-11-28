package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@SequenceGenerator(
        name="seq_style_hash_tag",
        sequenceName = "seq_style_hash_tag",
        initialValue = 1,
        allocationSize = 1
)
public class StyleHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_style_hash_tag")
    private Long id;

    @ManyToOne
    Style style;

    @ManyToOne
    HashTag hashTag;
}
