package com.project.kream.Repository;


import com.project.kream.Model.Entity.Delivery;
import com.project.kream.Model.enumclass.DeliveryStatus;
import com.project.kream.Model.response.DeliveryCntApiResponse;
import com.project.kream.Repository.Querydsl.DeliveryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryCustom {

    @Query("select count(0) from Delivery d where d.deliveryStatus = ?1")
    Long deliveryCnt(DeliveryStatus deliveryStatus);

    @Override
    Page<Delivery> SearchList(Long deliveryId, Long PurchaseId, DeliveryStatus deliveryStatus, String devCompany, Long trackNum, String regdate1, String regdate2, Pageable pageable);

}
