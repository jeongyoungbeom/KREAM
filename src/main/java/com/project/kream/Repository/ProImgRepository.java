package com.project.kream.Repository;


import com.project.kream.Model.Entity.ProImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProImgRepository extends JpaRepository<ProImg,Long> {
    List<ProImg> findAllByProductId(Long id);
}

