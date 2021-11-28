package com.project.kream.Repository;

import com.project.kream.Model.Entity.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {
    boolean existsByCustomerIdAndStyleReplyId(Long customerId, Long replyId);

    @Modifying
    @Transactional
    void deleteByCustomerIdAndStyleReplyId(Long customerId, Long replyId);

    @Modifying
    @Transactional
    void deleteAllByCustomerId(Long customerId);
}
