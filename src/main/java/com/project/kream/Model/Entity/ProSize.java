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
        name="seq_size",
        sequenceName = "seq_size",
        initialValue = 1,
        allocationSize = 1
)
public class ProSize {
    @Id
    @GeneratedValue
    private Long id;
    private String sizeType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
