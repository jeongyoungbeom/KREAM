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

    public Pagination(Page<?> list, int startPage, int endPage) {
        this.totalPages = list.getTotalPages();
        this.totalElements = list.getTotalElements();
        this.currentPage = list.getNumber();
        this.currentElements = list.getNumberOfElements();
        this.startPage = startPage;
        this.endPage = endPage;
    }

    public Pagination(Page<?> list) {
        this.totalPages = list.getTotalPages();
        this.totalElements = list.getTotalElements();
        this.currentPage = list.getNumber();
        this.currentElements = list.getNumberOfElements();
    }
}
