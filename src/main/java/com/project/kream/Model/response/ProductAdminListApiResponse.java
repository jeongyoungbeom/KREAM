package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductAdminListApiResponse {
    private Long id;
    private String modelNumber;
    private String brand;
    private Category category;
    private PostStatus postStatus;
    private LocalDateTime regdate;

}
