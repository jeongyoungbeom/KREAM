package com.project.kream.Repository;

import com.project.kream.Model.Entity.StyleCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleCustomerRepository extends JpaRepository<StyleCustomer, Long> {
    StyleCustomer findByCustomerId(Long customerId);
}
