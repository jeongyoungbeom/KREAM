package com.project.kream.Model.response;


import com.project.kream.Model.Entity.ProImg;
import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductApiResponse {
    private Long id;
    private String brand;
    private String collection;
    private Category category;
    private SubCategory subCategory;
    private String korName;
    private String name;
    private String gender;
    private String release;
    private Long releasePrice;
    private String modelNumber;
    private String color;
    private PostStatus postStatus;
    private LocalDateTime regdate;
    private Long amount;
    private Long mini;
    private Long maxx;
    private Long premium;
    private String origFileName;
}
