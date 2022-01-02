package com.project.kream.Model.Entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@SequenceGenerator(
        name="seq_style_reply",
        sequenceName = "seq_style_reply",
        initialValue = 1,
        allocationSize = 1
)
public class StyleReply extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_style_reply")
    private Long id;
    private String content;
    private Long hit;
    private Long parentId;
    private Long depth;
    private Long groupNum;
    private Long groupId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Style style;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "styleReply",cascade = CascadeType.REMOVE)
    private List<ReplyLike> replyLikeList;

    @OneToMany(mappedBy = "styleReply",cascade = CascadeType.REMOVE)
    private List<ReplyHashTag> replyHashTagList;

}
