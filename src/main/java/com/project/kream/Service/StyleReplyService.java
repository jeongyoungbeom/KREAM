package com.project.kream.Service;

import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleReply;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleReplyApiRequest;
import com.project.kream.Model.response.StyleHashTagNameApiResponse;
import com.project.kream.Model.response.StyleReplyDetailApiResponse;
import com.project.kream.Model.response.StyleReplyPopApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.ReplyLikeRepository;
import com.project.kream.Repository.StyleReplyRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StyleReplyService {
    private final StyleReplyRepository styleReplyRepository;
    private final StyleRepository styleRepository;
    private final CustomerRepository customerRepository;
    private final ReplyLikeService replyLikeService;
    private final ReplyLikeRepository replyLikeRepository;

    public Long create(Header<StyleReplyApiRequest> request) {
        StyleReplyApiRequest styleReplyApiRequest = request.getData();
        Long Reply = styleReplyApiRequest.getId();
        StyleReply styleReply;

        if(Reply != null){
            StyleReply newReply = styleReplyRepository.getById(Reply);
            Long groupNumMin = styleReplyRepository.groupNumMin(newReply.getGroupId(), newReply.getGroupNum(), newReply.getDepth());
            if(groupNumMin == 0){
                Long groupNumMax = styleReplyRepository.groupNumMax(newReply.getGroupId());
                styleReply = styleReplyRepository.save(styleReplyApiRequest.toEntity(styleRepository.getById(styleReplyApiRequest.getStyleId())
                        ,customerRepository.getById(styleReplyApiRequest.getCustomerId()), newReply.getId(), newReply.getDepth()+1,
                        groupNumMax+1,newReply.getGroupId()));

            }else{
                styleReplyRepository.updateGroupNum(newReply.getGroupId(), newReply.getGroupNum());
                styleReply = styleReplyRepository.save(styleReplyApiRequest.toEntity(styleRepository.getById(styleReplyApiRequest.getStyleId()),
                        customerRepository.getById(styleReplyApiRequest.getCustomerId()),newReply.getId(),
                        newReply.getDepth()+1,newReply.getGroupNum()+1, newReply.getGroupId()));
            }
        }else{

            styleReply = styleReplyRepository.save(styleReplyApiRequest.toEntity(styleRepository.getById(styleReplyApiRequest.getStyleId())
                    ,customerRepository.getById(styleReplyApiRequest.getCustomerId()),styleReplyRepository.getnextval(),0L, 0L, styleReplyRepository.getnextval()));
        }
        return styleReply.getId();
    }



    public Long update(Header<StyleReplyApiRequest> request){
        StyleReplyApiRequest styleReplyApiRequest = request.getData();
        StyleReply styleReply = styleReplyRepository.findById(styleReplyApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        styleReply.update(styleReplyApiRequest.getContent(), styleReplyApiRequest.getHit(), styleReplyApiRequest.getParentId(), styleReplyApiRequest.getDepth(), styleReplyApiRequest.getGroupNum(), styleReplyApiRequest.getGroupId(), styleReply.getStyle(),styleReply.getCustomer(),styleReply.getReplyLikeList(), styleReply.getReplyHashTagList());
        return styleReplyApiRequest.getId();
    }


    public Header<StyleReplyPopApiResponse> replyPop(Long styleId, Long customerId){
        Style style = styleRepository.getById(styleId);
        List<StyleReply> styleReplyList = styleReplyRepository.ReplyList(styleId);
        List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList = styleReplyList.stream()
                .map(styleReply -> new StyleReplyDetailApiResponse(styleReply,replyLikeService.liked(customerId, styleReply.getId()))).collect(Collectors.toList());

        List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                .map(StyleHashTagNameApiResponse::new).collect(Collectors.toList());

        return Header.OK(new StyleReplyPopApiResponse(style, styleHashTagNameApiResponseList, styleReplyDetailApiResponseList));
    }

    public void delete(Long id){
        replyLikeRepository.deleteAllByCustomerId(id);
        styleReplyRepository.replyDel(id);
    }


}
