package com.project.kream.Service;

import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleLike;
import com.project.kream.Model.request.StyleLikeApiRequest;
import com.project.kream.Model.response.StyleLikeApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.StyleLikeRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StyleLikeService extends BaseService<StyleLikeApiRequest, StyleLikeApiResponse, StyleLike> {
    private final CustomerRepository customerRepository;
    private final StyleRepository styleRepository;
    private final StyleLikeRepository styleLikeRepository;

    public Long like(Long customerId, Long styleId){
        if (!liked(customerId, styleId)){
            StyleLike styleLike = StyleLike.builder()
                    .customer(customerRepository.getById(customerId))
                    .style(styleRepository.getById(styleId))
                    .build();
            baseRepository.save(styleLike);
            styleRepository.updateStyleIdPlus(styleId);
        }else{
            styleLikeRepository.deleteByCustomerIdAndStyleId(customerId, styleId);
            styleRepository.updateStyleIdminus(styleId);
        }
        Long hit = styleRepository.styleHit(styleId);

        return hit;
    }

    public boolean liked(Long customerId, Long styleId){
        if (!styleLikeRepository.existsByCustomerIdAndStyleId(customerId, styleId)){
            return false;
        }
        return true;
    }
}
