package com.project.kream.Model.Entity;


import com.project.kream.Model.enumclass.AddressFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name="seq_address",
        sequenceName = "seq_address",
        initialValue = 1,
        allocationSize = 1
)
public class Address extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
    private Long id;
    private String name;
    private String hp;
    private String zipcode;
    private String detail1;
    private String detail2;
    @Enumerated(EnumType.STRING)
    private AddressFlag flag;

    @ManyToOne
    private Customer customer;
}
