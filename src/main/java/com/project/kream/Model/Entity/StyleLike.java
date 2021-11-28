package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@SequenceGenerator(
        name="seq_style_like",
        sequenceName = "seq_style_like",
        initialValue = 1,
        allocationSize = 1
)
public class StyleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_style_like")
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Style style;

}
