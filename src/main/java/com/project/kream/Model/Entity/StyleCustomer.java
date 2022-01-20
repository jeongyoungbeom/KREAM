package com.project.kream.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@SequenceGenerator(
        name="seq_style_customer",
        sequenceName = "seq_style_customer",
        initialValue = 1,
        allocationSize = 1
)
public class StyleCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_style_customer")
    private Long id;
    private String profileName;
    private String name;
    private String intro;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;

    public void update(String profileName, String name, String intro){
        this.profileName = profileName;
        this.name = name;
        this.intro = intro;
    }
}
