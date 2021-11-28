package com.project.kream.Repository;


import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleHashTag;
import com.project.kream.Repository.Querydsl.StyleCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StyleRepository extends JpaRepository<Style, Long>, StyleCustom {


    @Modifying
    @Transactional
    @Query("UPDATE Style AS s set s.hit = s.hit+1 WHERE s.id = ?1")
    void updateStyleIdPlus(Long styleId);

    @Modifying
    @Transactional
    @Query("UPDATE Style AS s set s.hit = s.hit-1 WHERE s.id = ?1")
    void updateStyleIdminus(Long styleId);

    // 사용자 게시글
    List<Style> findAllByCustomerId(Long customerId);

    Long countByCustomerId(Long customerId);

    // 태구
    @Query("select s from Style s where s.id in(select s.style.id from ReplyHashTag r join StyleReply s on s.id = r.styleReply.id where r.hashTag.id = (select h.id from HashTag h where h.tagName = ?1)) or s.id in(select s.id from StyleHashTag h join Style s on s.id = h.style.id where h.hashTag.id = (select h.id from HashTag h where h.tagName = ?1))")
    List<Style> findAllBy(String tagName);

    @Query("select s from Style s where s.customer.id in (select f.following.id from Follow f where f.follower.id = ?1)")
    List<Style> findAllByFollowerId(Long followerId);

    // 스타일픽
    List<Style> findTop8ByOrderByHitDesc();

    @Override
    List<Style> HashTagList(String tagName);

    @Query("SELECT count(0) FROM Style")
    Long StyleCnt();

    @Query("select s.hit from Style s where s.id = ?1")
    Long styleHit(Long styleId);
}
