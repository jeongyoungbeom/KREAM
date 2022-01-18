package com.project.kream.Service;


import com.project.kream.Model.Entity.ProSize;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProsizeApiRequest;
import com.project.kream.Model.response.ProsizeApiResponse;
import com.project.kream.Repository.ProductRepository;
import com.project.kream.Repository.ProSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProsizeService extends BaseService<ProsizeApiRequest, ProsizeApiResponse, ProSize> {
    private final ProSizeRepository prosizeRepository;
    private final ProductRepository productRepository;


    public Header<Long> create(Header<ProsizeApiRequest> request) {
        ProsizeApiRequest prosizeApiRequest = request.getData();
        ProSize newprosize = baseRepository.save(prosizeApiRequest.toEntity(productRepository.getById(prosizeApiRequest.getProductId())));
        return Header.OK(newprosize.getId());
    }

    @Transactional
    public Header<Long> update(Header<ProsizeApiRequest> request) {
        ProsizeApiRequest prosizeApiRequest = request.getData();
        ProSize prosize = prosizeRepository.findById(prosizeApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));

        prosize.update(prosizeApiRequest.getSizeType());
        return Header.OK(prosizeApiRequest.getId());
    }

    public Header delete(Long id){
        Optional<ProSize> prosize = prosizeRepository.findById(id);
        return prosize.map(pro -> {
            baseRepository.delete(pro);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    public Header<List<ProsizeApiResponse>> list(){
        List<ProSize> styleEntities = baseRepository.findAll();
        List<ProsizeApiResponse> styleApiResponseList = styleEntities.stream()
                .map(ProsizeApiResponse::new)
                .collect(Collectors.toList());
        return Header.OK(styleApiResponseList);
    }
}
