package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductUserListApiResponse {
    private Long id;
    private String brand;
    private Category category;
    private SubCategory subCategory;
    private String korName;
    private String name;
    private String origFileName;
    private Long price;
    private Long amount;
    private Long mini;
    private Long maxx;
    private String premium;
    private List<CustomerMypageSalesInfoApiResponse> customerMypageSalesInfoApiResponseList;
}
