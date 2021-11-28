package com.project.kream.Model.Entity;


import com.project.kream.Model.enumclass.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne
    private Purchase purchase;
}
