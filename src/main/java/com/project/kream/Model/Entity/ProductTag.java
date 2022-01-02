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
        name="seq_tag",
        sequenceName = "seq_tag",
        initialValue = 1,
        allocationSize = 1
)
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tag")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Style style;

    @OneToOne
    private Product product;

}
