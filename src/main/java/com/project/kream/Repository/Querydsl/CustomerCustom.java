package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerCustom {
    Page<Customer> CustomerSearch(CustomerType customerType, CustomerRank customerRank, String userid, String email, String startDatetime, String endDatetime, Pageable pageable);
}
