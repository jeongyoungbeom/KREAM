package com.project.kream.Repository.Querydsl;


import com.project.kream.Model.Entity.Withdrawal;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.project.kream.Model.Entity.QPurchase.purchase;
import static com.project.kream.Model.Entity.QWithdrawal.withdrawal;

@RequiredArgsConstructor
public class WithdrawalRepositoryImpl implements WithdrawalCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Withdrawal> WithdrawalSearch(String email, String hp, String startDatetime, String endDatetime, Pageable pageable){
        QueryResults<Withdrawal> result = queryFactory.selectFrom(withdrawal)
                .where(eqEmail(email), eqHp(hp), eqRegdateBetween(startDatetime, endDatetime))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqEmail(String email){
        if(StringUtils.isEmpty(email)){
            return null;
        }
        return withdrawal.email.eq(email);
    }
    private BooleanExpression eqHp(String hp){
        if(StringUtils.isEmpty(hp)){
            return null;
        }
        return withdrawal.hp.eq(hp);
    }
    private BooleanExpression eqRegdateBetween(String regdate1, String regdate2){
        if(ObjectUtils.isEmpty(regdate1) || ObjectUtils.isEmpty(regdate2)){
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDateTime startDatetime = LocalDate.parse(regdate1, formatter).atStartOfDay();
        LocalDateTime endDatetime = LocalDate.parse(regdate2, formatter).atTime(23, 59, 59);

        return withdrawal.regdate.between(startDatetime, endDatetime);
    }
}
