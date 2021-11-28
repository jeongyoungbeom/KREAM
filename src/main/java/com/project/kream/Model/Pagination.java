package com.project.kream.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
