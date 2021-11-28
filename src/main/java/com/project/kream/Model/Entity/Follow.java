package com.project.kream.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name="seq_follow",
        sequenceName = "seq_follow",
        initialValue = 1,
        allocationSize = 1
)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_follow")
    private Long id;

    @ManyToOne
    private Customer following;

    @ManyToOne
    private Customer follower;
}
