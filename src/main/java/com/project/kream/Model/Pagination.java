package com.project.kream.Model;

import com.project.kream.Model.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {
    private int totalPages;
    private Long totalElements;
    private int currentPage;
    private int currentElements;
    private int startPage;
    private int endPage;

    public Pagination(Page<?> customerList, int startPage, int endPage) {
        this.totalPages = customerList.getTotalPages();
        this.totalElements = customerList.getTotalElements();
        this.currentPage = customerList.getNumber();
        this.currentElements = customerList.getNumberOfElements();
        this.startPage = startPage;
        this.endPage = endPage;
    }
}
