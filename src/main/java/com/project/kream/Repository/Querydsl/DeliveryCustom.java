package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Delivery;
import com.project.kream.Model.enumclass.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryCustom {
    Page<Delivery> SearchList(Long deliveryId, Long PurchaseId, DeliveryStatus deliveryStatus, String devCompany, Long trackNum, String regdate1, String regdate2, Pageable pageable);

}
