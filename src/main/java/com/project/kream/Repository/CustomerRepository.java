package com.project.kream.Repository;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.enumclass.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Override
    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);

    Customer getByEmail(String useremail);

    Page<Customer> findAllByType(CustomerType customerType, Pageable pageable);

    // 이메일 찾기
    Customer findByEmailAndHp(String email, String hp);
    Optional<Customer> findByEmail(String email);


    // 비밀번호 찾기
    Customer findByHp(String hp);

    @Query("SELECT count(0) FROM Customer where type = ?1")
    Long CustomerCnt(CustomerType customerType);

    @Query("SELECT count(0) FROM Customer where regdate between ?1 and ?2")
    Long NowCustomerCnt(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

}
