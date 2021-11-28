package com.project.kream.Service;


import com.project.kream.Model.Entity.ProSize;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProsizeApiRequest;
import com.project.kream.Model.response.ProsizeApiResponse;
import com.project.kream.Repository.ProductRepository;
import com.project.kream.Repository.ProSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProsizeService extends BaseService<ProsizeApiRequest, ProsizeApiResponse, ProSize> {
    private final ProSizeRepository prosizeRepository;
    private final ProductRepository productRepository;


    public Header<ProsizeApiResponse> create(Header<ProsizeApiRequest> request) {
        ProsizeApiRequest prosizeApiRequest = request.getData();
        ProSize prosize = ProSize.builder()
                .sizeType(prosizeApiRequest.getSizeType())
                .product(productRepository.getById(prosizeApiRequest.getProductId()))
                .build();

        ProSize newprosize = baseRepository.save(prosize);

        return Header.OK(response(newprosize));
    }

    public Header<ProsizeApiResponse> update(Header<ProsizeApiRequest> request) {
        ProsizeApiRequest prosizeApiRequest = request.getData();
        Optional<ProSize> prosize = prosizeRepository.findById(prosizeApiRequest.getId());

        return prosize.map(pro ->{
            pro.setSizeType(prosizeApiRequest.getSizeType());
            return pro;
        }).map(pro -> baseRepository.save(pro))
                .map(pro ->response(pro))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
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
                .map(style ->response(style))
                .collect(Collectors.toList());

        return Header.OK(styleApiResponseList);
    }

    public ProsizeApiResponse response(ProSize prosize){
        ProsizeApiResponse prosizeApiResponse = ProsizeApiResponse.builder()
                .id(prosize.getId())
                .productId(prosize.getProduct().getId())
                .sizeType(prosize.getSizeType())
                .build();
        return prosizeApiResponse;

    }



}
