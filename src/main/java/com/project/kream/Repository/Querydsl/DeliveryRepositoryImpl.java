package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Delivery;
import com.project.kream.Model.enumclass.DeliveryStatus;
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

import static com.project.kream.Model.Entity.QDelivery.delivery;
import static com.project.kream.Model.Entity.QPurchase.purchase;

@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Delivery> SearchList(Long deliveryId, Long purchaseId, DeliveryStatus deliveryStatus, String devCompany, Long trackNum, String regdate1, String regdate2, Pageable pageable) {
        QueryResults<Delivery> result = queryFactory.selectFrom(delivery)
                .where(eqDeliveryId(deliveryId), eqPurchaseId(purchaseId), eqDeliveryStatus(deliveryStatus), eqDevCompany(devCompany), eqTrackNum(trackNum), eqRegdateBetween(regdate1, regdate2))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqDeliveryId(Long deliveryId){
        if(ObjectUtils.isEmpty(deliveryId)){
            return null;
        }
        return delivery.id.eq(deliveryId);
    }

    private BooleanExpression eqPurchaseId(Long purchaseId){
        if(ObjectUtils.isEmpty(purchaseId)){
            return null;
        }
        return delivery.purchase.id.eq(purchaseId);
    }

    private BooleanExpression eqDeliveryStatus(DeliveryStatus deliveryStatus){
        if(ObjectUtils.isEmpty(deliveryStatus)){
            return null;
        }
        return delivery.deliveryStatus.eq(deliveryStatus);
    }

    private BooleanExpression eqDevCompany(String devCompany){
        if(ObjectUtils.isEmpty(devCompany)){
            return null;
        }
        return delivery.devCompany.eq(devCompany);
    }

    private BooleanExpression eqTrackNum(Long trackNum){
        if(ObjectUtils.isEmpty(trackNum)){
            return null;
        }
        return delivery.trackNum.eq(trackNum);
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
