package com.project.kream.Repository;


import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import com.project.kream.Repository.Querydsl.PurchaseCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase,Long>, PurchaseCustom {
    Optional<Purchase> findById(Long productId);

    @Query("select distinct m.sizeType FROM Purchase AS m WHERE  m.sizeType = ?1 AND m.price = ?2")
    public String findBySizeTypeAndPrice(String size, Long price);

    @Query("select distinct m.price FROM Purchase AS m WHERE m.price = ?1 AND m.sizeType = ?2")
    public Long findByPriceAndSizeType(Long price, String size );

    Long countByPriceAndSizeType(Long price, String size);

    List<Purchase> findByStatus1(PurchaseStatus1 purchaseStatus1);

    @Query("select p.id from Purchase as p where p.price = (select max(p.price) from Purchase as p where p.product.id = ?1 and p.sizeType = ?2) and p.regdate = (select min(p.regdate) from Purchase as p where p.product.id" +
            " = ?1 and p.sizeType = ?2)")
    public Long findByProductIdAndSizeType(Long productId, String size);

    @Query("select MAX(m.price) FROM Purchase AS m WHERE m.sizeType=?1 AND m.product.id=?2")
    public Long findBySizeTypeAndProductId(String size, Long productId);

    @Query("select MAX(m.price) FROM Purchase AS m WHERE m.sizeType=?1")
    public Long findBySizeType(String size);

    @Query("select MAX(m.price) FROM Purchase AS m")
    public Long max();

    @Modifying
    @Transactional
    @Query("update Purchase as p set p.sales.id = ?1, p.status1 = ?2, p.status2 = ?3 where p.id = ?4")
    public void findByProductIdAndId(Long salesId, PurchaseStatus1 purchaseStatus1, PurchaseStatus2 purchaseStatus2, Long purchaseId);

    @Override
    Page<Purchase> SearchList(Long purchaseId, PurchaseStatus2 purchaseStatus2, PurchaseStatus3 purchaseStatus3, Long productId, String regdate1, String regdate2, Pageable pageable);

    @Query("select p from Purchase p where p.status2 = ?1")
    Page<Purchase> findAllByStatus2(PurchaseStatus2 purchaseStatus2, Pageable pageable);

    @Query("select p from Purchase p where p.customer.id = ?1 and p.regdate between ?2 and ?3")
    List<Purchase> findAllByCustomerIdAndRegdate(Long customerId, LocalDateTime regdate1, LocalDateTime regdate2);

}
