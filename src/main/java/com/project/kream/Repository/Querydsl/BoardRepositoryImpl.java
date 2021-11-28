package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Board;
import com.project.kream.Model.enumclass.BoardCategory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.project.kream.Model.Entity.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Board> notice(Pageable pageable) {
        QueryResults<Board> result = queryFactory.selectFrom(board)
                .where(eqCategory(BoardCategory.쇼룸).or(eqCategory(BoardCategory.이벤트)).or(eqCategory(BoardCategory.공지)))
                .orderBy(board.regdate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<Board> faq(Pageable pageable) {
        QueryResults<Board> result = queryFactory.selectFrom(board)
                .where(eqCategory(BoardCategory.이용정책).or(eqCategory(BoardCategory.공통)).or(eqCategory(BoardCategory.구매)).or(eqCategory(BoardCategory.판매)))
                .orderBy(new CaseBuilder()
                        .when(eqCategory(BoardCategory.이용정책)).then(1)
                        .when(eqCategory(BoardCategory.공통)).then(2)
                        .when(eqCategory(BoardCategory.구매)).then(3)
                        .when(eqCategory(BoardCategory.판매)).then(4).otherwise(5).asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqCategory(BoardCategory boardCategory){
        return board.category.eq(boardCategory);
    }
}
