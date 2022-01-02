package com.project.kream.Repository;

import com.project.kream.Model.Entity.CardInfo;
import org.codehaus.plexus.component.annotations.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {
    List<CardInfo> findAllByCustomerId(Long id);
}
