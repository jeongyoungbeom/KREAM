package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@SequenceGenerator(
        name="seq_reply_like",
        sequenceName = "seq_reply_like",
        initialValue = 1,
        allocationSize = 1
)
public class ReplyLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reply_like")
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private StyleReply styleReply;
}
