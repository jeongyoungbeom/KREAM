package com.project.kream.Model.request;

import com.project.kream.Model.Entity.ProImg;
import com.project.kream.Model.Entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ProimgApiRequest {

    public ProImg toEntity(String origFileName, Long fileSize, String safeFile, Product product){
        return ProImg.builder()
                .origFileName(origFileName)
                .filePath(safeFile)
                .fileSize(fileSize)
                .product(product)
                .build();
    }

}
