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

    @ManyToOne
    private Product product;
}
