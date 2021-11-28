package com.project.kream.Repository;

import com.project.kream.Model.Entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductTagRepository extends JpaRepository<ProductTag,Long> {
    List<ProductTag> findAllByProductId(Long id);

    @Modifying
    @Transactional
    void deleteByProductIdAndStyleId(Long productId, Long styleId);

    @Transactional
    void deleteAllByStyleId(Long styleId);

}
