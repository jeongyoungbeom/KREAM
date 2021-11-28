package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Withdrawal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WithdrawalCustom {
    Page<Withdrawal> WithdrawalSearch(String email, String hp, String startRegdate, String EndRegdate, Pageable pageable);
}
