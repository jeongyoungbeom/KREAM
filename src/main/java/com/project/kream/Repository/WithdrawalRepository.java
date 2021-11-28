package com.project.kream.Repository;

import com.project.kream.Model.Entity.Withdrawal;
import com.project.kream.Repository.Querydsl.WithdrawalCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long>, JpaSpecificationExecutor<Withdrawal>, WithdrawalCustom {

    @Override
    Page<Withdrawal> findAll(Specification<Withdrawal> spec, Pageable pageable);

    Page<Withdrawal> WithdrawalSearch(String email, String hp, String startRegdate, String endRegdate, Pageable pageable);

    @Query("SELECT count(0) FROM Withdrawal where regdate between ?1 and ?2")
    Long NowWithdrawalCnt(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

}
