package com.project.kream.Repository.Specification;

import com.project.kream.Model.Entity.Withdrawal;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.WithdrawalApiRequest;
import com.project.kream.Repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class WithdrawalSpecification {
    private final WithdrawalRepository withdrawalRepository;

    public static Specification<Withdrawal> likeEmail(String email){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }
    public static Specification<Withdrawal> likeHp(String hp){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("hp"), "%" + hp + "%");
    }
    public static Specification<Withdrawal> betweenRegdate(LocalDateTime startDatetime, LocalDateTime endDatetime){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("Regdate"), startDatetime, endDatetime);
    }

    public Page<Withdrawal> searchWithdrawalList(Header<WithdrawalApiRequest> withdrawal, Pageable pageable){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

        LocalDateTime startDatetime;
        LocalDateTime endDatetime;
        Specification<Withdrawal> spec = null;

        if(withdrawal.getData().getEmail() != null){
            if(spec == null){
                spec = Specification.where(WithdrawalSpecification.likeEmail(withdrawal.getData().getEmail()));
            }else{
                spec = spec.and(WithdrawalSpecification.likeEmail(withdrawal.getData().getEmail()));
            }
        }
        if(withdrawal.getData().getHp() != null){
            if(spec == null){
                spec = Specification.where(WithdrawalSpecification.likeHp(withdrawal.getData().getHp()));
            }else{
                spec = spec.and(WithdrawalSpecification.likeHp(withdrawal.getData().getHp()));
            }
        }
        if(withdrawal.getData().getStartDatetime() != null && withdrawal.getData().getEndDatetime() != null){
            startDatetime = LocalDate.parse(withdrawal.getData().getStartDatetime(), formatter).atStartOfDay();
            endDatetime = LocalDate.parse(withdrawal.getData().getEndDatetime(), formatter).atTime(23, 59, 59);
            if(spec == null){
                spec = Specification.where(WithdrawalSpecification.betweenRegdate(startDatetime, endDatetime));
            }else{
                spec = spec.and(WithdrawalSpecification.betweenRegdate(startDatetime, endDatetime));
            }
        }
        return withdrawalRepository.findAll(spec, pageable);
    }
}
