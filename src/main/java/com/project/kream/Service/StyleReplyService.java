package com.project.kream.Service;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Entity.StyleCustomer;
import com.project.kream.Model.Entity.StyleReply;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleReplyApiRequest;
import com.project.kream.Model.response.StyleHashTagNameApiResponse;
import com.project.kream.Model.response.StyleReplyApiResponse;
import com.project.kream.Model.response.StyleReplyDetailApiResponse;
import com.project.kream.Model.response.StyleReplyPopApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.ReplyLikeRepository;
import com.project.kream.Repository.StyleReplyRepository;
import com.project.kream.Repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StyleReplyService extends BaseService<StyleReplyApiRequest, StyleReplyApiResponse, StyleReply> {
    private final StyleReplyRepository styleReplyRepository;
    private final StyleRepository styleRepository;
    private final CustomerRepository customerRepository;
    private final ReplyLikeService replyLikeService;
    private final ReplyLikeRepository replyLikeRepository;

    public Header<StyleReplyApiResponse> create(Header<StyleReplyApiRequest> request) {
        StyleReplyApiRequest styleReplyApiRequest = request.getData();
        Long Reply = styleReplyApiRequest.getId();
        StyleReply styleReply;

        if(Reply != null){
            StyleReply newReply = baseRepository.getById(Reply);
            Long groupNumMin = styleReplyRepository.groupNumMin(newReply.getGroupId(), newReply.getGroupNum(), newReply.getDepth());
            if(groupNumMin == 0){
                Long groupNumMax = styleReplyRepository.groupNumMax(newReply.getGroupId());
                styleReply = StyleReply.builder()
                        .style(styleRepository.getById(styleReplyApiRequest.getStyleId()))
                        .customer(customerRepository.getById(styleReplyApiRequest.getCustomerId()))
                        .content(styleReplyApiRequest.getContent())
                        .hit(0L)
                        .parentId(newReply.getId())
                        .depth(newReply.getDepth()+1)
                        .groupNum(groupNumMax+1)
                        .groupId(newReply.getGroupId())
                        .build();
            }else{
                styleReplyRepository.updateGroupNum(newReply.getGroupId(), newReply.getGroupNum());
                styleReply = StyleReply.builder()
                        .style(styleRepository.getById(styleReplyApiRequest.getStyleId()))
                        .customer(customerRepository.getById(styleReplyApiRequest.getCustomerId()))
                        .content(styleReplyApiRequest.getContent())
                        .hit(0L)
                        .parentId(newReply.getId())
                        .depth(newReply.getDepth()+1)
                        .groupNum(newReply.getGroupNum()+1)
                        .groupId(newReply.getGroupId())
                        .build();
            }
        }else{
            styleReply = StyleReply.builder()
                    .style(styleRepository.getById(styleReplyApiRequest.getStyleId()))
                    .customer(customerRepository.getById(styleReplyApiRequest.getCustomerId()))
                    .content(styleReplyApiRequest.getContent())
                    .hit(0L)
                    .parentId(styleReplyRepository.getnextval())
                    .depth(0L)
                    .groupNum(0L)
                    .groupId(styleReplyRepository.getnextval())
                    .build();
        }
        StyleReply newstyleReply = baseRepository.save(styleReply);
        return Header.OK(response(newstyleReply));
    }

    public Header<StyleReplyApiResponse> update(Header<StyleReplyApiRequest> request) {
        StyleReplyApiRequest styleReplyApiRequest = request.getData();
        Optional<StyleReply> StyleReply = styleReplyRepository.findById(styleReplyApiRequest.getId());
        return StyleReply.map(repl ->{
                    repl.setId(styleReplyApiRequest.getId());
                    repl.setStyle(styleRepository.getById(styleReplyApiRequest.getStyleId()));
                    repl.setContent(styleReplyApiRequest.getContent());
                    repl.setHit(styleReplyApiRequest.getHit());
                    repl.setDepth(styleReplyApiRequest.getDepth());
                    return repl;
                }).map(repl -> baseRepository.save(repl))
                .map(repl ->response(repl))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터가 없습니다."));
    }


    public StyleReplyApiResponse response(StyleReply styleReply){
        StyleReplyApiResponse styleReplyApiResponse = StyleReplyApiResponse.builder()
                .id(styleReply.getId())
                .styleId(styleReply.getStyle().getId())
                .customerId(styleReply.getCustomer().getId())
                .content(styleReply.getContent())
                .hit(styleReply.getHit())
                .parentId(styleReply.getParentId())
                .depth(styleReply.getDepth())
                .regdate(styleReply.getRegdate())
                .groupNum(styleReply.getGroupNum())
                .groupId(styleReply.getGroupId())
                .build();
        return styleReplyApiResponse;
    }


    public Header<StyleReplyPopApiResponse> replyPop(Long styleId, Long customerId){
        Style style = styleRepository.getById(styleId);
        List<StyleReply> styleReplyList = styleReplyRepository.ReplyList(styleId);
        List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList = styleReplyList.stream()
                .map(styleReply -> {
                    Customer customer1 = styleReply.getCustomer();
                    StyleCustomer styleCustomer1 = customer1.getStyleCustomerList().get(0);
                    StyleReplyDetailApiResponse styleReplyDetailApiResponse = StyleReplyDetailApiResponse.builder()
                            .id(styleReply.getId())
                            .customerId(customer1.getId())
                            .customerUserid(styleCustomer1.getProfileName())
                            .customerOriginFile(customer1.getImage())
                            .content(styleReply.getContent())
                            .depth(styleReply.getDepth())
                            .hit(styleReply.getHit())
                            .regdate(styleReply.getRegdate())
                            .replyBoolean(replyLikeService.liked(customerId, styleReply.getId()))
                            .build();
                    return styleReplyDetailApiResponse;
                }).collect(Collectors.toList());

        List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                .map(styleHashTag -> {
                    StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                            .tagName(styleHashTag.getHashTag().getTagName())
                            .build();
                    return styleHashTagNameApiResponse;
                }).collect(Collectors.toList());

        StyleReplyPopApiResponse styleReplyPopApiResponse = StyleReplyPopApiResponse.builder()
                .styleCustomerId(style.getCustomer().getStyleCustomerList().get(0).getProfileName())
                .content(style.getContent())
                .regdate(style.getRegdate())
                .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                .customerImg(style.getCustomer().getImage())
                .hit(style.getHit())
                .styleReplyDetailApiResponseList(styleReplyDetailApiResponseList)
                .build();

        return Header.OK(styleReplyPopApiResponse);
    }

    public void delete(Long id){
        replyLikeRepository.deleteAllByCustomerId(id);
        styleReplyRepository.replyDel(id);
    }


}
