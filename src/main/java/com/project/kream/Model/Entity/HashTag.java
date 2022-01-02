package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @OneToMany(mappedBy = "hashTag",cascade = CascadeType.REMOVE)
    private List<StyleHashTag> styleHashTagList;

    @OneToMany(mappedBy = "hashTag",cascade = CascadeType.REMOVE)
    private List<ReplyHashTag> replyHashTagList;
}
