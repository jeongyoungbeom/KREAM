package com.project.kream.Repository;

import com.project.kream.Model.Entity.StyleHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface StyleHashTagRepository extends JpaRepository<StyleHashTag, Long> {


    @Transactional
    void deleteAllByStyleId(Long styleId);
}
