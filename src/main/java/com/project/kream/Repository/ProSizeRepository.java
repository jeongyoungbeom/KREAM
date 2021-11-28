package com.project.kream.Repository;

import com.project.kream.Model.Entity.ProSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProSizeRepository extends JpaRepository<ProSize,Long> {
    List<ProSize> findByProductId(Long id);
}
