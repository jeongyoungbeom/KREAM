package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.HashTag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.project.kream.Model.Entity.QHashTag.hashTag;
import static com.project.kream.Model.Entity.QStyleHashTag.styleHashTag;

@RequiredArgsConstructor
public class HashTagRepositoryImpl implements HashTagCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> HashTagNameTop5() {
        List<String> result = queryFactory.select(hashTag.tagName).from(styleHashTag)
                .join(hashTag).on(hashTag.id.eq(styleHashTag.hashTag.id))
                .groupBy(hashTag.tagName)
                .orderBy(hashTag.tagName.count().desc())
                .limit(5)
                .fetch();

        return result;
    }
}
