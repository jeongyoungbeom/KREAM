package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.enumclass.*;
import com.project.kream.Model.response.ProductApiResponse;
import com.project.kream.Model.response.ProductPopularApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCustom {
    List<ProductApiResponse> ProductSearch(String orderFlag, Category category, SubCategory subCategory, String brand1, String brand2, String brand3, String brand4, String brand5, String brand6, String brand7, String brand8, String brand9, String brand10, String brand11, String brand12, String brand13, String brand14, String brand15, String brand16, String brand17, String brand18, String brand19, String brand20, String gender, String collection1, String collection2,
                                           String collection3, String collection4, String collection5, String collection6, String collection7, String collection8, String collection9, String collection10, String size1, String size2, String size3, String size4, String size5, String size6, String size7, String size8, String size9, String size10, String size11, Long price1, Long price2, Long price3, Long price4, Long price5, Long price6);

    List<ProductPopularApiResponse> Popular();

    List<Product> NewLowers(PurchaseStatus2 purchaseStatus2);

    List<Product> NewHighest(SalesStatus2 salesStatus2);
}
