package com.project.kream.Repository.Specification;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Header;
import com.project.kream.Model.enumclass.Category;
import com.project.kream.Model.enumclass.PostStatus;
import com.project.kream.Model.request.ProductApiRequest;
import com.project.kream.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductSpecification {
    private final ProductRepository productRepository;

    public static Specification<Product> equalId(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }
    public static Specification<Product> equalbrand(String brand) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand"), brand);
    }
    public static Specification<Product> equalPostStatus(PostStatus postStatus) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("postStatus"), postStatus);
    }
    public static Specification<Product> equalCategory(Category category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

    public Page<Product> searchProductList(Header<ProductApiRequest> product, Pageable pageable){

        Specification<Product> spec = null;
        if(product.getData().getId() != null){
            spec = Specification.where(ProductSpecification.equalId(product.getData().getId()));
        }
        if(product.getData().getBrand() != null){
            if(spec == null){
                spec = Specification.where(ProductSpecification.equalbrand(product.getData().getBrand()));
            }else {
                spec = spec.and(ProductSpecification.equalbrand(product.getData().getBrand()));
            }
        }

        if(product.getData().getPostStatus() != null){
            if(spec == null){
                spec = Specification.where(ProductSpecification.equalPostStatus(product.getData().getPostStatus()));
            }else {
                spec = spec.and(ProductSpecification.equalPostStatus(product.getData().getPostStatus()));
            }
        }

        if(product.getData().getCategory() != null){
            if(spec == null){
                spec = Specification.where(ProductSpecification.equalCategory(product.getData().getCategory()));
            }else {
                spec = spec.and(ProductSpecification.equalCategory(product.getData().getCategory()));
            }
        }

        return productRepository.findAll(spec, pageable);
    }
}
