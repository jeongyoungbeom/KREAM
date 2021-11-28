package com.project.kream.Repository;

import com.project.kream.Model.Entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowingIdAndFollowerId(Long followingId, Long followerId);

    @Modifying
    @Transactional
    void deleteByFollowingIdAndFollowerId(Long followingId, Long followerId);

    Long countByFollowerId(Long followerId);

    Long countByFollowingId(Long follwingId);
}
