package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.project.kream.Model.Entity.QPurchase.purchase;


@RequiredArgsConstructor
public class PurchaseRepositoryImpl implements PurchaseCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Purchase> SearchList(Long purchaseId, PurchaseStatus2 purchaseStatus2, PurchaseStatus3 purchaseStatus3, Long productId, String regdate1, String regdate2, Pageable pageable) {
        QueryResults<Purchase> result = queryFactory.selectFrom(purchase)
                .where(eqPurchaseId(purchaseId), eqStatus2(purchaseStatus2), eqStatus3(purchaseStatus3), eqProductId(productId), eqRegdateBetween(regdate1, regdate2))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqPurchaseId(Long purchaseId){
        if(ObjectUtils.isEmpty(purchaseId)){
            return null;
        }
        return purchase.id.eq(purchaseId);
    }

    private BooleanExpression eqStatus2(PurchaseStatus2 purchaseStatus2){
        if(ObjectUtils.isEmpty(purchaseStatus2)){
            return null;
        }
        return purchase.status2.eq(purchaseStatus2);
    }

    private BooleanExpression eqStatus3(PurchaseStatus3 purchaseStatus3){
        if (ObjectUtils.isEmpty(purchaseStatus3)){
            return null;
        }
        return purchase.status3.eq(purchaseStatus3);
    }

    private BooleanExpression eqProductId(Long productId){
        if (ObjectUtils.isEmpty(productId)){
            return null;
        }
        return purchase.product.id.eq(productId);
    }

    private BooleanExpression eqRegdateBetween(String regdate1, String regdate2){
        if(ObjectUtils.isEmpty(regdate1) || ObjectUtils.isEmpty(regdate2)){
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDateTime startDatetime = LocalDate.parse(regdate1, formatter).atStartOfDay();
        LocalDateTime endDatetime = LocalDate.parse(regdate2, formatter).atTime(23, 59, 59);

        return purchase.regdate.between(startDatetime, endDatetime);
    }
}
