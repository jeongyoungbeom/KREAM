package com.project.kream.Model.Entity;


import com.project.kream.Model.enumclass.DeliveryStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@SequenceGenerator(
        name="seq_delivery",
        sequenceName = "seq_delivery",
        initialValue = 1,
        allocationSize = 1
)
public class Delivery extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_delivery")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private String devCompany;
    private Long trackNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Purchase purchase;
}
