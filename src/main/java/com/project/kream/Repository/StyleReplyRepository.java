package com.project.kream.Repository;


import com.project.kream.Model.Entity.StyleReply;
import com.project.kream.Repository.Querydsl.StyleReplyCustom;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StyleReplyRepository extends JpaRepository<StyleReply, Long>, StyleReplyCustom {
    Long countByStyleId(Long id);

    @Query(value = "SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE sequence_name = 'SEQ_STYLE_REPLY'", nativeQuery = true)
    Long getnextval();

    @Override
    Long groupNumMin(Long pGroupId, Long pGroupNum, Long pDepth);

    @Override
    Long groupNumMax(Long pGroupId);

    @Override
    @Transactional
    void updateGroupNum(Long pGroupId, Long pGroupNum);

    @Override
    List<StyleReply> ReplyList(Long styleId);

    @Modifying
    @Transactional
    @Query("UPDATE StyleReply AS s set s.hit = s.hit+1 WHERE s.id = ?1")
    void updateStyleIdPlus(Long replyId);

    @Modifying
    @Transactional
    @Query("UPDATE StyleReply AS s set s.hit = s.hit-1 WHERE s.id = ?1")
    void updateStyleIdminus(Long replyId);

    @Query("select s.hit from StyleReply s where s.id = ?1")
    Long replyHit(Long replyId);

    @Modifying
    @Transactional
    @Query("delete from StyleReply s where s.id = ?1 or s.parentId=?1")
    void replyDel(Long id);

}
