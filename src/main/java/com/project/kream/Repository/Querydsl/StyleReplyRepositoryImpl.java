package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.StyleReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.project.kream.Model.Entity.QStyleReply.styleReply;

@RequiredArgsConstructor
public class StyleReplyRepositoryImpl implements StyleReplyCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Long groupNumMin(Long pGroupId, Long pGroupNum, Long pDepth) {
        Long groupNum = queryFactory.select(styleReply.groupNum.min().coalesce(0L).as(styleReply.groupNum)).from(styleReply)
                .where(styleReply.groupId.eq(pGroupId).and(styleReply.groupNum.gt(pGroupNum).and(styleReply.depth.loe(pDepth))))
                .fetchOne();

        return groupNum;
    }

    @Override
    public Long groupNumMax(Long pGroupId) {
        Long groupNum = queryFactory.select(styleReply.groupNum.max()).from(styleReply)
                .where(styleReply.groupId.eq(pGroupId))
                .fetchOne();

        return groupNum;
    }

    @Override
    public void updateGroupNum(Long pGroupId, Long pGroupNum) {
        queryFactory.update(styleReply).set(styleReply.groupNum, styleReply.groupNum.add(1))
                .where(styleReply.groupId.eq(pGroupId).and(styleReply.groupNum.gt(pGroupNum)))
                .execute();
    }

    @Override
    public List<StyleReply> ReplyList(Long styleId) {
        List<StyleReply> styleReplyList = queryFactory.selectFrom(styleReply)
                .where(styleReply.style.id.eq(styleId))
                .orderBy(styleReply.groupId.asc(), styleReply.groupNum.asc())
                .fetch();

        return styleReplyList;
    }
}
