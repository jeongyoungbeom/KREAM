package com.project.kream.Repository;

import com.project.kream.Model.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long customerId);



    Account getByCustomerId(Long customerId);
}
