package com.project.kream.Service;

import com.project.kream.Model.Entity.HashTag;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.HashTagApiRequest;
import com.project.kream.Model.response.HashTagApiResponse;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashTagService extends BaseService<HashTagApiRequest, HashTagApiResponse, HashTag> {
    private final StyleRepository styleRepository;


    public Long create(String tagName) {
        HashTag hashTag = HashTag.builder()
                .tagName(tagName)
                .build();

        HashTag newhashtag = baseRepository.save(hashTag);
        return newhashtag.getId();

    }

    public Header<HashTagApiResponse> update(Header<HashTagApiRequest> request) {
        return null;
    }


    public HashTagApiResponse response(HashTag hashTag){

        HashTagApiResponse hashTagApiResponse = HashTagApiResponse.builder()
                .id(hashTag.getId())
                .tagName(hashTag.getTagName())
                .build();

        return hashTagApiResponse;
    }

    public Header<List<HashTagApiResponse>> list(){
        List<HashTag> hashTagList = baseRepository.findAll();

        List<HashTagApiResponse> hashTagApiResponseList = hashTagList.stream()
                .map(hashTag -> response(hashTag))
                .collect(Collectors.toList());

        return Header.OK(hashTagApiResponseList);

    }

}
