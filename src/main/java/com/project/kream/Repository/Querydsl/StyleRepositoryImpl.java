package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Style;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.project.kream.Model.Entity.QHashTag.hashTag;
import static com.project.kream.Model.Entity.QStyle.style;
import static com.project.kream.Model.Entity.QStyleHashTag.styleHashTag;

@RequiredArgsConstructor
public class StyleRepositoryImpl implements StyleCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Style> HashTagList(String tagName) {
        List<Style> hashTagList = queryFactory.selectFrom(style)
                .where(
                        style.id.in(JPAExpressions.select(styleHashTag.style.id).from(styleHashTag).where(styleHashTag.hashTag.id.in(
                                JPAExpressions.select(hashTag.id).from(hashTag).where(hashTag.tagName.eq(tagName))
                        )))
                ).orderBy(style.hit.desc())
                .limit(8)
                .fetch();
        return hashTagList;
    }
}
