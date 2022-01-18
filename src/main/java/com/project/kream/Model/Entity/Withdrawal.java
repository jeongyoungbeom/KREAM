package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@SequenceGenerator(
        name="seq_withdrawal",
        sequenceName = "seq_withdrawal",
        initialValue = 1,
        allocationSize = 1
)
public class Withdrawal extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_withdrawal")
    private Long id;
    private String email;
    private String hp;
}
