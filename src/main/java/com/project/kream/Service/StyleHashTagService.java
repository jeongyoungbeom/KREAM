package com.project.kream.Service;

import com.project.kream.Model.request.StyleHashTagApiRequest;
import com.project.kream.Repository.HashTagRepository;
import com.project.kream.Repository.StyleHashTagRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StyleHashTagService {
    private final StyleRepository styleRepository;
    private final HashTagRepository hashTagRepository;
    private final StyleHashTagRepository styleHashTagRepository;

    public void create(Long styleId, Long hashTagId){
        StyleHashTagApiRequest styleHashTagApiRequest = StyleHashTagApiRequest.builder()
                .styleId(styleId)
                .hashTagId(hashTagId)
                .build();
        styleHashTagRepository.save(styleHashTagApiRequest.toEntity(styleRepository.getById(styleId), hashTagRepository.getById(hashTagId)));
    }
}
