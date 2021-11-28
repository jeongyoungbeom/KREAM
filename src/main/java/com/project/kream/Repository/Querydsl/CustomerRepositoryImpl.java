package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerType;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;

import static com.project.kream.Model.Entity.QCustomer.customer;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerCustom{
    private final JPAQueryFactory queryFactory;
    @Override
    public Page<Customer> CustomerSearch(CustomerType customerType, CustomerRank customerRank, String userid, String email, String startDatetime, String endDatetime, Pageable pageable){
        QueryResults<Customer> result = queryFactory.selectFrom(customer)
                .where(eqEmail(email), eqType(customerType), eqRank(customerRank), eqUserId(userid), eqRegdate(startDatetime, endDatetime))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return  new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqEmail(String email){
        if(StringUtils.isEmpty(email)){
            return null;
        }
        return customer.email.contains(email);
    }

    private BooleanExpression eqUserId(String userid){
        if(StringUtils.isEmpty(userid)){
            return null;
        }
        return customer.userid.contains(userid);
    }

    private BooleanExpression eqType(CustomerType customerType){
        if(ObjectUtils.isEmpty(customerType)){
            return null;
        }
        return customer.type.eq(customerType);
    }

    private BooleanExpression eqRank(CustomerRank customerRank){
        if(ObjectUtils.isEmpty(customerRank)){
            return null;
        }
        return customer.rank.eq(customerRank);
    }


    private BooleanExpression eqRegdate(String startDatetime, String endDatetime){
        if (StringUtils.isEmpty(startDatetime)){
            return null;
        }
        return customer.regdate.between(LocalDateTime.parse(startDatetime), LocalDateTime.parse(endDatetime));
    }


}
