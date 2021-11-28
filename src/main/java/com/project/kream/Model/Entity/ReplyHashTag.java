package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@SequenceGenerator(
        name="seq_reply_hash_tag",
        sequenceName = "seq_reply_hash_tag",
        initialValue = 1,
        allocationSize = 1
)
public class ReplyHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reply_hash_tag")
    private Long id;

    @ManyToOne
    StyleReply styleReply;

    @ManyToOne
    HashTag hashTag;
}
