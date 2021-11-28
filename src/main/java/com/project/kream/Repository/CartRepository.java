package com.project.kream.Repository;

import com.project.kream.Model.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Long countByProductIdAndSizeTypeAndCustomerId(Long productId, String size, Long customerId);

    Cart findByProductIdAndSizeType(Long productId, String sizeType);

}
