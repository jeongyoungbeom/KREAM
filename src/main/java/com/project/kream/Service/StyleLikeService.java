package com.project.kream.Service;

import com.project.kream.Model.request.StyleLikeApiRequest;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.StyleLikeRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StyleLikeService {
    private final CustomerRepository customerRepository;
    private final StyleRepository styleRepository;
    private final StyleLikeRepository styleLikeRepository;

    public Long like(Long customerId, Long styleId){
        if (!liked(customerId, styleId)){
            StyleLikeApiRequest styleLikeApiRequest = new StyleLikeApiRequest();
            styleLikeRepository.save(styleLikeApiRequest.toEntity(customerRepository.getById(customerId),styleRepository.getById(styleId)));
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
