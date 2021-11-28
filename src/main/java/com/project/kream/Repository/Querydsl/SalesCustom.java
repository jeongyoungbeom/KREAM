package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.enumclass.SalesStatus3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesCustom {
    Page<Sales> SearchList(Long salesId, SalesStatus2 salesStatus2, SalesStatus3 salesStatus3, Long productId, String regdate1, String regdate2, Pageable pageable);


}
