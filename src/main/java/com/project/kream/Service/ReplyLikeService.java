package com.project.kream.Service;

import com.project.kream.Model.Entity.ReplyLike;
import com.project.kream.Model.request.ReplyLikeApiRequest;
import com.project.kream.Model.response.ReplyLikeApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.ReplyLikeRepository;
import com.project.kream.Repository.StyleReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyLikeService extends BaseService<ReplyLikeApiRequest, ReplyLikeApiResponse, ReplyLike> {
    private final CustomerRepository customerRepository;
    private final StyleReplyRepository styleReplyRepository;
    private final ReplyLikeRepository replyLikeRepository;

    public Long like(Long customerId, Long replyId){
        if(!liked(customerId, replyId)){
            ReplyLikeApiRequest replyLikeApiRequest = new ReplyLikeApiRequest();
            baseRepository.save(replyLikeApiRequest.toEntity(customerRepository.getById(customerId), styleReplyRepository.getById(replyId)));
            styleReplyRepository.updateStyleIdPlus(replyId);
        }else{
            replyLikeRepository.deleteByCustomerIdAndStyleReplyId(customerId, replyId);
            styleReplyRepository.updateStyleIdminus(replyId);
        }
        return styleReplyRepository.replyHit(replyId);
    }

    public boolean liked(Long customerId, Long replyId){
        if(!replyLikeRepository.existsByCustomerIdAndStyleReplyId(customerId, replyId)){
            return false;
        }
        return true;
    }


}
