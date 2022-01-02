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
        name="seq_style",
        sequenceName = "seq_style",
        initialValue = 1,
        allocationSize = 1
)
@EntityListeners(AuditingEntityListener.class)
public class Style extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_style")
    private Long id;
    private String content;
    private Long hit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleReply> styleReplyList;

    @OneToMany(mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<ProductTag> productTagList;

    @OneToMany(mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleImg> styleImgList;

    @OneToMany(mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleLike> styleLikeList;

    @OneToMany(mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleHashTag> styleHashTagList;
}
