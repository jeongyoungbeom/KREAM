package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustom {
    Page<Board> notice(Pageable pageable);

    Page<Board> faq(Pageable pageable);
}
