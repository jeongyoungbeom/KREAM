package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@SequenceGenerator(
        name="seq_proimg",
        sequenceName = "seq_proimg",
        initialValue = 1,
        allocationSize = 1
)
public class ProImg {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_proimg")
    private Long id;
    private String origFileName;
    private String filePath;
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


}
