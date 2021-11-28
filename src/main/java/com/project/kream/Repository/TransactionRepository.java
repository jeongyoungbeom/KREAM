package com.project.kream.Repository;

import com.project.kream.Model.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByProductIdOrderByRegdateAsc(Long productId);

    List<Transaction> findTop2ByProductIdOrderByRegdateDesc(Long productId);

//    @Query("select t.price FROM Transaction AS t WHERE t.product.id = ?1 AND t.Regdate = (select max(r.Regdate) FROM Transaction AS r WHERE r.product.id = ?1)")
//    public Transaction findByProductId(Long productId);

    @Query("SELECT count(0) FROM Transaction ")
    Long TransactionCnt();

    @Query("SELECT count(0) FROM Transaction where regdate between ?1 and ?2")
    Long NowTransactionCnt(LocalDateTime localDateTime1, LocalDateTime localDateTime2);
}
