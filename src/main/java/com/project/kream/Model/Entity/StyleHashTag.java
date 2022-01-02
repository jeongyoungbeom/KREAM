package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    Style style;

    @ManyToOne(fetch = FetchType.LAZY)
    HashTag hashTag;
}
