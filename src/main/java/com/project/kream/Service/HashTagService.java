package com.project.kream.Service;

import com.project.kream.Model.Entity.HashTag;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.HashTagApiRequest;
import com.project.kream.Model.response.HashTagApiResponse;
import com.project.kream.Repository.HashTagRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    public Long create(String tagName) {
        HashTagApiRequest hashTagApiRequest = HashTagApiRequest.builder()
                .tagName(tagName)
                .build();
        HashTag newhashtag = hashTagRepository.save(hashTagApiRequest.toEntity());
        return newhashtag.getId();
    }

    public Header<List<HashTagApiResponse>> list(){
        List<HashTag> hashTagList = hashTagRepository.findAll();
        List<HashTagApiResponse> hashTagApiResponseList = hashTagList.stream()
                .map(HashTagApiResponse::new)
                .collect(Collectors.toList());
        return Header.OK(hashTagApiResponseList);
    }
}
