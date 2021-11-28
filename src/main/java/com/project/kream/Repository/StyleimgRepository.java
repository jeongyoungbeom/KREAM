package com.project.kream.Repository;


import com.project.kream.Model.Entity.StyleImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StyleimgRepository extends JpaRepository<StyleImg, Long> {
    Long countByStyleId(Long id);

    List<StyleImg> findAllByStyleId(Long StyleId);

}
