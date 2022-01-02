package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer following;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer follower;
}
