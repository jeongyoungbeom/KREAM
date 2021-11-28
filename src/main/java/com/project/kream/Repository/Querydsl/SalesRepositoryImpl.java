package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
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
import static com.project.kream.Model.Entity.QSales.sales;

@RequiredArgsConstructor
public class SalesRepositoryImpl implements SalesCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Sales> SearchList(Long salesId, SalesStatus2 salesStatus2, SalesStatus3 salesStatus3, Long productId, String regdate1, String regdate2, Pageable pageable) {
        QueryResults<Sales> result = queryFactory.selectFrom(sales)
                .where(eqSalesId(salesId), eqStatus2(salesStatus2), eqStatus3(salesStatus3), eqProductId(productId), eqRegdateBetween(regdate1, regdate2))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }



    private BooleanExpression eqSalesId(Long purchaseId){
        if(ObjectUtils.isEmpty(purchaseId)){
            return null;
        }
        return sales.id.eq(purchaseId);
    }

    private BooleanExpression eqStatus2(SalesStatus2 salesStatus2){
        if(ObjectUtils.isEmpty(salesStatus2)){
            return null;
        }
        return sales.status2.eq(salesStatus2);
    }

    private BooleanExpression eqStatus3(SalesStatus3 salesStatus3){
        if (ObjectUtils.isEmpty(salesStatus3)){
            return null;
        }
        return sales.status3.eq(salesStatus3);
    }

    private BooleanExpression eqProductId(Long productId){
        if (ObjectUtils.isEmpty(productId)){
            return null;
        }
        return sales.product.id.eq(productId);
    }

    private BooleanExpression eqRegdateBetween(String regdate1, String regdate2){
        if(ObjectUtils.isEmpty(regdate1) || ObjectUtils.isEmpty(regdate2)){
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDateTime startDatetime = LocalDate.parse(regdate1, formatter).atStartOfDay();
        LocalDateTime endDatetime = LocalDate.parse(regdate2, formatter).atTime(23, 59, 59);

        return sales.regdate.between(startDatetime, endDatetime);
    }
}
