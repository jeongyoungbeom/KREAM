package com.project.kream.Repository.Specification;

import com.project.kream.Model.Entity.Board;
import com.project.kream.Model.Header;
import com.project.kream.Model.enumclass.BoardCategory;
import com.project.kream.Model.request.BoardApiRequest;
import com.project.kream.Repository.BoardRepository;
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
public class BoardSpecification {
    private final BoardRepository boardRepository;

    public static Specification<Board> likeTitle(String title){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }
    public static Specification<Board> likeContent(String content) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("content"), "%" + content + "%");
    }
    public static Specification<Board> equalId(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }
    public static Specification<Board> equalBoardCategory(BoardCategory boardCategory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), boardCategory);
    }
    public static Specification<Board> betweenRegdate1(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("regdate"), startDatetime, endDatetime);
    }

    public Page<Board> searchCustomerList(Header<BoardApiRequest> board, Pageable pageable){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

        LocalDateTime startDatetime;
        LocalDateTime endDatetime;
        Specification<Board> spec = null;

        if(board.getData().getId() != null){
                spec = Specification.where(BoardSpecification.equalId(board.getData().getId()));
        }
        if(board.getData().getContent() != null){
            if(spec == null){
                spec = Specification.where(BoardSpecification.likeContent(board.getData().getContent()));
            }else {
                spec = spec.and(BoardSpecification.likeContent(board.getData().getContent()));
            }
        }
        if(board.getData().getTitle() != null){
            if(spec == null){
                spec = Specification.where(BoardSpecification.likeTitle(board.getData().getTitle()));
            }else {
                spec = spec.and(BoardSpecification.likeTitle(board.getData().getTitle()));
            }
        }
        if(board.getData().getCategory() != null){
            if(spec == null){
                spec = Specification.where(BoardSpecification.equalBoardCategory(board.getData().getCategory()));
            }else {
                spec = spec.and(BoardSpecification.equalBoardCategory(board.getData().getCategory()));
            }

        }
        if(board.getData().getStartDatetime() != null && board.getData().getEndDatetime() != null){
            startDatetime = LocalDate.parse(board.getData().getStartDatetime(), formatter).atStartOfDay();
            endDatetime = LocalDate.parse(board.getData().getEndDatetime(), formatter).atTime(23, 59, 59);
            if(spec == null){
                spec = Specification.where(BoardSpecification.betweenRegdate1(startDatetime, endDatetime));
            }else {
                spec = spec.and(BoardSpecification.betweenRegdate1(startDatetime, endDatetime));
            }
        }
        return boardRepository.findAll(spec, pageable);
    }
}
