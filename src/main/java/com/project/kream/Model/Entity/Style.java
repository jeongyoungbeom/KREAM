package com.project.kream.Model.Entity;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ManyToOne
    private Customer customer;

    @OneToMany(fetch=FetchType.LAZY ,mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleReply> styleReplyList;

    @OneToMany(fetch=FetchType.LAZY ,mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<ProductTag> productTagList;

    @OneToMany(fetch=FetchType.LAZY ,mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleImg> styleImgList;

    @OneToMany(fetch=FetchType.LAZY ,mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleLike> styleLikeList;

    @OneToMany(fetch=FetchType.LAZY ,mappedBy = "style",cascade = CascadeType.REMOVE)
    private List<StyleHashTag> styleHashTagList;
}
