package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@SequenceGenerator(
        name="seq_hashtag",
        sequenceName = "seq_hashtag",
        initialValue = 1,
        allocationSize = 1
)
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hashtag")
    private Long id;
    private String tagName;

    @OneToMany(fetch=FetchType.LAZY ,mappedBy = "hashTag",cascade = CascadeType.REMOVE)
    private List<StyleHashTag> styleHashTagList;

    @OneToMany(fetch=FetchType.LAZY ,mappedBy = "hashTag",cascade = CascadeType.REMOVE)
    private List<ReplyHashTag> replyHashTagList;
}
