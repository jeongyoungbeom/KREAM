package com.project.kream.Model.request;

import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class ProductApiRequest {
    private Long id;
    private String brand;
    private String brand2;
    private String brand3;
    private String brand4;
    private String brand5;
    private String brand6;
    private String brand7;
    private String brand8;
    private String brand9;
    private String brand10;
    private String brand11;
    private String brand12;
    private String brand13;
    private String brand14;
    private String brand15;
    private String brand16;
    private String brand17;
    private String brand18;
    private String brand19;
    private String brand20;
    private String collection;
    private String collection2;
    private String collection3;
    private String collection4;
    private String collection5;
    private String collection6;
    private String collection7;
    private String collection8;
    private String collection9;
    private String collection10;
    private Category category;
    private SubCategory subCategory;
    private String name;
    private String korName;
    private String gender;
    private String release;
    private Long releasePrice;
    private String modelNumber;
    private String color;
    private PostStatus postStatus;
    private String regdate;
    private String startDatetime;
    private String endDatetime;
    private String defaultDatetime;
    private String sizeType;
    private String sizeType2;
    private String sizeType3;
    private String sizeType4;
    private String sizeType5;
    private String sizeType6;
    private String sizeType7;
    private String sizeType8;
    private String sizeType9;
    private String sizeType10;
    private String sizeType11;
    private Long price1;
    private Long price2;
    private Long price3;
    private Long price4;
    private Long price5;
    private Long price6;
    private String orderFlag;
}
