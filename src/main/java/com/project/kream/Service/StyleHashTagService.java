package com.project.kream.Service;

import com.project.kream.Model.Entity.StyleHashTag;
import com.project.kream.Model.request.StyleHashTagApiRequest;
import com.project.kream.Model.response.StyleHashTagApiResponse;
import com.project.kream.Repository.HashTagRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StyleHashTagService extends BaseService<StyleHashTagApiRequest, StyleHashTagApiResponse, StyleHashTag> {
    private final StyleRepository styleRepository;
    private final HashTagRepository hashTagRepository;

    public void create(Long styleId, Long hashTagId){
        StyleHashTagApiRequest styleHashTagApiRequest = StyleHashTagApiRequest.builder()
                .styleId(styleId)
                .hashTagId(hashTagId)
                .build();
        baseRepository.save(styleHashTagApiRequest.toEntity(styleRepository.getById(styleId), hashTagRepository.getById(hashTagId)));
    }
}
