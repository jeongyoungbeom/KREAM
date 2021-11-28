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
public class ProductTagService extends BaseService<ProductTagApiRequest, ProductTagApiResponse, ProductTag>{
    private final ProductRepository productRepository;
    private final ProductTagRepository productTagRepository;
    private final StyleRepository styleRepository;

    public Header<ProductTagApiResponse> create(Long productId, Long styleId) {
        ProductTag productTag = ProductTag.builder()
                .style(styleRepository.getById(styleId))
                .product(productRepository.getById(productId))
                .build();

        ProductTag newtagdata = baseRepository.save(productTag);

        return Header.OK(response(newtagdata));
    }

    public Header<ProductTagApiResponse> update(Header<ProductTagApiRequest> request) {
        return null;
    }

    public Header<ProductTagApiResponse> read(Long id) {
        return productTagRepository.findById(id)
                .map(pro -> response(pro))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

        public ProductTagApiResponse response(ProductTag productTag){
            ProductTagApiResponse productTagApiResponse = ProductTagApiResponse.builder()
                    .id(productTag.getId())
                    .styleId(productTag.getStyle().getId())
                    .productId(productTag.getProduct().getId())
                    .name(productTag.getProduct().getName())
//                    .originFileName(productTag.getProduct().getProImgList().get(0).getOrigFileName())
                    .releasePrice(productTag.getProduct().getReleasePrice())
                    .build();
            return productTagApiResponse;
        }


        public Header<List<ProductTagApiResponse>> list(){
            List<ProductTag> productTagList = baseRepository.findAll();

            List<ProductTagApiResponse> productTagApiResponseList = productTagList.stream()
                    .map(tagData -> response(tagData))
                    .collect(Collectors.toList());

            return Header.OK(productTagApiResponseList);

        }
    }


