package com.project.kream.Repository;


import com.project.kream.Model.Entity.HashTag;
import com.project.kream.Repository.Querydsl.HashTagCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long>, HashTagCustom {
    boolean existsByTagName(String tagName);

    HashTag getByTagName(String tagName);

    @Override
    List<String> HashTagNameTop5();
}
