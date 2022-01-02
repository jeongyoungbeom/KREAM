package com.project.kream.Repository;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.enumclass.*;
import com.project.kream.Model.response.ProductApiResponse;
import com.project.kream.Model.response.ProductPopularApiResponse;
import com.project.kream.Repository.Querydsl.ProductCustom;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product>, ProductCustom {
    @Override
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    @Override
    List<Product> findAll(Specification<Product> spec);

    List<Product> findAllByCollectionAndPostStatus(String collection, PostStatus postStatus);

    @Query("SELECT count(0) FROM Product ")
    Long ProductCnt();

    @Query("SELECT m FROM Product AS m WHERE m.postStatus = ?1 and LOWER(brand || collection || category || name || korName || modelNumber) like %?2%")
    Page<Product> findAllByBrandOrCollectionOrCategoryOrNameOrKorNameOrModelNumber(PostStatus postStatus, String keywords, Pageable pageable);

    @Query("SELECT count(*) FROM Product AS m WHERE m.postStatus = ?1 and LOWER(brand || collection || category || name || korName || modelNumber) like %?2%")
    Long countAllByByBrandOrCollectionOrCategoryOrNameOrKorNameOrModelNumber(PostStatus postStatus, String keywords);
    Long countByPostStatus(PostStatus postStatus);

    @Override
    List<ProductApiResponse> ProductSearch(String orderFlag, Category category, SubCategory subCategory, String brand1, String brand2, String brand3, String brand4, String brand5, String brand6, String brand7, String brand8, String brand9, String brand10, String brand11, String brand12, String brand13, String brand14, String brand15, String brand16, String brand17, String brand18, String brand19, String brand20, String gender, String collection1, String collection2,
                                           String collection3, String collection4, String collection5, String collection6, String collection7, String collection8, String collection9, String collection10, String size1, String size2, String size3, String size4, String size5, String size6, String size7, String size8, String size9, String size10, String size11, Long price1, Long price2, Long price3, Long price4, Long price5, Long price6);

    // 발매상품
    List<Product> findTop12ByPostStatusOrderByRegdateDesc(PostStatus postStatus);

    // 발매예정
    List<Product> findTop12ByPostStatusOrderByReleaseAsc(PostStatus postStatus);

    // 인기상품
    @Override
    List<ProductPopularApiResponse> Popular();

    // 추천상품
    List<Product> findTop12ByCollectionAndPostStatus(String collection, PostStatus postStatus);

    List<Product> findTop12ByBrandAndPostStatus(String brand, PostStatus postStatus);

    @Override
    List<Product> NewLowers(PurchaseStatus2 purchaseStatus2);

    @Override
    List<Product> NewHighest(SalesStatus2 salesStatus2);
}
