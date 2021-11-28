package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@SequenceGenerator(
        name="seq_tag",
        sequenceName = "seq_tag",
        initialValue = 1,
        allocationSize = 1
)
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tag")
    private Long id;

    @ManyToOne
    private Style style;

    @OneToOne
    private Product product;

}
