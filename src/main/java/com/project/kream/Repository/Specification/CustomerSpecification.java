package com.project.kream.Repository.Specification;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Header;
import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerType;
import com.project.kream.Model.request.CustomerApiRequest;
import com.project.kream.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerSpecification {
    private final CustomerRepository customerRepository;


    public static Specification<Customer>equalType(CustomerType customerType){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), customerType);
    }
    public static Specification<Customer> equaluserId(String userid) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userid"), userid);
    }
    public static Specification<Customer> equalemail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }
    public static Specification<Customer> equalCustomerRank(CustomerRank customerRank) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("rank"), customerRank);
    }
    public static Specification<Customer> betweenRegdate1(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("regdate"), startDatetime, endDatetime);
    }


    public Page<Customer> searchCustomerList(Header<CustomerApiRequest> customer, Pageable pageable){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

        LocalDateTime startDatetime;
        LocalDateTime endDatetime;

        Specification<Customer> spec = Specification.where(CustomerSpecification.equalType(customer.getData().getType()));

        if(customer.getData().getUserid() != null){
            spec = spec.and(CustomerSpecification.equaluserId(customer.getData().getUserid()));
        }
        if(customer.getData().getEmail() != null){
            spec = spec.and(CustomerSpecification.equalemail(customer.getData().getEmail()));

        }
        if(customer.getData().getRank() != null){
            spec = spec.and(CustomerSpecification.equalCustomerRank(customer.getData().getRank()));
        }
        if(customer.getData().getStartDatetime() != null && customer.getData().getEndDatetime() != null){
            startDatetime = LocalDate.parse(customer.getData().getStartDatetime(), formatter).atStartOfDay();
            endDatetime = LocalDate.parse(customer.getData().getEndDatetime(), formatter).atTime(23, 59, 59);
            spec = spec.and(CustomerSpecification.betweenRegdate1(startDatetime, endDatetime));
        }

        return customerRepository.findAll(spec, pageable);
    }
}