package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAdminDetailApiResponse {
    private Long id;
    private String brand;
    private String korName;
    private String name;
    private String collection;
    private String release;
    private String gender;
    private Long releasePrice;
    private String modelNumber;
    private String color;
    private List<ProimgPathApiResponse> proimgPathApiResponseList;
    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;



}
