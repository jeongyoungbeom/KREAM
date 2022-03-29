package com.project.kream.Service;


import com.project.kream.Model.Entity.ProductTag;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProductTagApiRequest;
import com.project.kream.Model.response.ProductTagApiResponse;
import com.project.kream.Repository.ProductRepository;
import com.project.kream.Repository.ProductTagRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductTagService {
    private final ProductRepository productRepository;
    private final ProductTagRepository productTagRepository;
    private final StyleRepository styleRepository;

    public Header<Long> create(Long productId, Long styleId) {
        ProductTagApiRequest productTagApiRequest = new ProductTagApiRequest();
        ProductTag newtagdata = productTagRepository.save(productTagApiRequest.toEntity(styleRepository.getById(styleId), productRepository.getById(productId)));
        return Header.OK(newtagdata.getId());
    }

    public Header<ProductTagApiResponse> read(Long id) {
        return Header.OK(new ProductTagApiResponse(productTagRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."))));
    }

        public Header<List<ProductTagApiResponse>> list(){
            List<ProductTag> productTagList = productTagRepository.findAll();
            List<ProductTagApiResponse> productTagApiResponseList = productTagList.stream()
                    .map(ProductTagApiResponse::new)
                    .collect(Collectors.toList());
            return Header.OK(productTagApiResponseList);

        }
    }


