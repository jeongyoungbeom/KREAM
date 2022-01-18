package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name="seq_product",
        sequenceName = "seq_product",
        initialValue = 1,
        allocationSize = 1
)
public class Product extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
    private Long id;
    private String brand;
    private String collection;
    private String name;
    private String korName;
    private String gender;
    private String release;
    private Long releasePrice;
    private String modelNumber;
    private String color;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval=true)
    private List<Purchase> purchaseList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval=true)
    private List<Sales> salesList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval=true)
    private List<ProImg> proImgList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval=true)
    private List<ProSize> proSizeList;

    public void update(String brand, String collection, String name, String korName, String gender, String release, Long releasePrice, String modelNumber, String color, Category category, SubCategory subCategory, PostStatus postStatus) {
        this.brand = brand;
        this.collection = collection;
        this.name = name;
        this.korName = korName;
        this.gender = gender;
        this.release = release;
        this.releasePrice = releasePrice;
        this.modelNumber = modelNumber;
        this.color = color;
        this.category = category;
        this.subCategory = subCategory;
        this.postStatus = postStatus;
    }

}
