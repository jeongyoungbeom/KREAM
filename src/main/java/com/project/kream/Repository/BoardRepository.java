package com.project.kream.Repository;

import com.project.kream.Model.Entity.Board;
import com.project.kream.Model.enumclass.BoardCategory;
import com.project.kream.Repository.Querydsl.BoardCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board>, BoardCustom {

    List<Board> findAllByCategory(BoardCategory category);

    @Override
    Page<Board> findAll(Specification<Board> spec, Pageable pageable);

    @Override
    Page<Board> notice(Pageable pageable);

    @Override
    Page<Board> faq(Pageable pageable);
}
