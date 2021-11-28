package com.project.kream.Repository;

import com.project.kream.Model.Entity.StyleLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface StyleLikeRepository extends JpaRepository<StyleLike, Long> {
    boolean existsByCustomerIdAndStyleId(Long customerId, Long styleId);

    @Modifying
    @Transactional
    void deleteByCustomerIdAndStyleId(Long customerId, Long styleId);
}
