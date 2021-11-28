package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.PurchaseStatus3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseCustom {
    Page<Purchase> SearchList(Long purchaseId, PurchaseStatus2 purchaseStatus2, PurchaseStatus3 purchaseStatus3, Long productId, String regdate1, String regdate2, Pageable pageable);
}
