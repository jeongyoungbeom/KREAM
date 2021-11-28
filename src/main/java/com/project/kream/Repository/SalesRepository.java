package com.project.kream.Repository;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import com.project.kream.Repository.Querydsl.SalesCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales,Long>, SalesCustom {
    List<Sales> findByStatus1(SalesStatus1 salesStatus1);

    @Query("select distinct m.sizeType FROM Sales AS m WHERE m.sizeType = ?1 AND m.price = ?2")
    public String findBySizeTypeAndPrice(String size, Long price);

    @Query("select distinct m.price FROM Sales AS m WHERE  m.price = ?1 AND m.sizeType = ?2")
    public Long findByPriceAndSizeType(Long price, String size);

    Long countByPriceAndSizeType(Long price, String size);

    @Query("select s.id from Sales as s where s.price = (select min(s.price) from Sales as s where s.product.id = ?1 and s.sizeType = ?2) and s.regdate = (select min(s.regdate) from Sales as s where s.product.id" +
            " = ?1 and s.sizeType = ?2)")
    public Long findByProductIdAndSizeType(Long productId, String size);

    @Query("select MIN(m.price) FROM Sales AS m WHERE m.sizeType=?1 AND m.product.id=?2 AND m.status1 = '판매입찰' ")
    public Long findBySizeTypeAndProductIdAndStatus1(String size, Long productId);

    @Query("select MIN(m.price) FROM Sales AS m WHERE m.product.id=?1")
    public Long findByProductId(Long id);

    @Query("select MIN(m.price) FROM Sales AS m WHERE m.sizeType=?1 AND m.product.id = ?2")
    public Long findBySizeTypeAndProductId(String size, Long productId);

    @Query("select MIN(m.price) FROM Sales AS m")
    public Long min();

    @Modifying
    @Transactional
    @Query("update Sales as s set s.purchase.id = ?1, s.status1 = ?2, s.status2 = ?3 where s.id = ?4")
    public void findByProductIdAndId(Long purchaseId, SalesStatus1 salesStatus1, SalesStatus2 salesStatus2, Long salesId);



    @Override
    Page<Sales> SearchList(Long salesId, SalesStatus2 salesStatus2, SalesStatus3 salesStatus3, Long productId, String regdate1, String regdate2, Pageable pageable);

    @Query("select s from Sales s where s.customer.id = ?1 and s.regdate between ?2 and ?3")
    List<Sales> findAllByCustomerIdAndRegdate(Long customerId, LocalDateTime regdate1, LocalDateTime regdate2);
}
