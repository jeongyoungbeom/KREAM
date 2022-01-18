package com.project.kream.Service;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.StyleApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StyleService extends BaseService<StyleApiRequest, StyleApiResponse, Style> {
    private final StyleRepository styleRepository;
    private final StyleReplyRepository styleReplyRepository;
    private final ProductTagService productTagService;
    private final HashTagService hashTagService;
    private final StyleimgService styleimgService;
    private final StyleimgRepository styleImgRepository;
    private final CustomerRepository customerRepository;
    private final SalesRepository salesRepository;
    private final ProductTagRepository productTagRepository;
    private final StyleHashTagRepository styleHashTagRepository;
    private final FollowRepository followRepository;
    private final HashTagRepository hashTagRepository;
    private final StyleHashTagService styleHashTagService;
    private final StyleLikeService styleLikeService;
    private final ReplyLikeService replyLikeService;
    private final FollowService followService;

    public Header<StyleApiResponse> create(StyleApiRequest request, MultipartHttpServletRequest multipart) {
        Style style = Style.builder()
                .customer(customerRepository.getById(request.getCustomerId()))
                .content(request.getContent())
                .hit(0L)
                .build();
        Style newStyle = baseRepository.save(style);

        List<MultipartFile> fileList = multipart.getFiles("files");
        String path = "/Users/soyounjeong/EditKream/src/main/resources/static/lib/styleImg";

        for(MultipartFile mf : fileList){
            String originFileName = mf.getOriginalFilename();
            long fileSize = mf.getSize();

            String safeFile = path + originFileName;

            try{
                mf.transferTo(new File(safeFile));
                styleimgService.create(originFileName, fileSize,  safeFile,  newStyle.getId());
            }catch (IllegalStateException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        for(Long id : request.getProductId()){
            productTagService.create(id, newStyle.getId());
        }

        for(String name : request.getTagName()){
            if(hashTagRepository.existsByTagName(name)){
                HashTag HashTag = hashTagRepository.getByTagName(name);
                styleHashTagService.create(newStyle.getId(), HashTag.getId());
            }else{
                Long hashTagId = hashTagService.create(name);
                styleHashTagService.create(newStyle.getId(), hashTagId);
            }
        }
        return Header.OK(response(newStyle));
    }

    public Header<StyleApiResponse> update(StyleApiRequest request) {
        Optional<Style> optionalStyle = styleRepository.findById(request.getId());
        return optionalStyle.map(style -> {
                    style.setContent(request.getContent());

                    productTagRepository.deleteAllByStyleId(request.getId());
                    styleHashTagRepository.deleteAllByStyleId(request.getId());

                    if(request.getProductId().length != 0) {
                        for (Long id : request.getProductId()) {
                            productTagService.create(id, style.getId());
                        }
                    }

                    if(request.getTagName().length != 0) {
                        for (String Name : request.getTagName()) {
                            if(hashTagRepository.existsByTagName(Name)){
                                HashTag HashTag = hashTagRepository.getByTagName(Name);
                                styleHashTagService.create(request.getId(), HashTag.getId());
                            }else{
                                Long hashTagId = hashTagService.create(Name);
                                styleHashTagService.create(request.getId(), hashTagId);
                            }
                        }
                    }
                    return style;
                }).map(style -> baseRepository.save(style))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
    }

    public Header<StyleApiResponse> upload(Long id, MultipartHttpServletRequest multiRequest){
        List<MultipartFile> fileList = multiRequest.getFiles("files");
        String path = "/Users/soyounjeong/EditKream/src/main/resources/static/lib/styleImg";

        List<StyleImg> styleImgList = styleImgRepository.findAllByStyleId(id);
        styleImgList.stream().map(styleImg ->{
            File file = new File(styleImg.getFilePath());
            if(file.exists()){
                file.delete();
            }
            styleImgRepository.delete(styleImg);
            return Header.OK();
        }).collect(Collectors.toList());

        for(MultipartFile mf : fileList){
            String originFileName = mf.getOriginalFilename();
            long fileSize = mf.getSize();
            String safeFile = path + originFileName;

            try{
                mf.transferTo(new File(safeFile));
                styleimgService.create(originFileName, fileSize,  safeFile,  id);
            }catch (IllegalStateException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return Header.OK();
    }

    // 히트 순서
    public Header<List<StyleHitListApiResponse>> styleHitList(Long sessionId) {
        List<Style> styleList = baseRepository.findAll();
        List<String> stringList = hashTagRepository.HashTagNameTop5();
        List<StyleHitListApiResponse> styleListApiResponseHitList = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                            .map(styleHashTag -> {
                                StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                                        .tagName(styleHashTag.getHashTag().getTagName())
                                        .build();
                                return styleHashTagNameApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleProductTagApiResponse> styleProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleProductTagApiResponse styleProductTagApiResponse = StyleProductTagApiResponse.builder()
                                        .productId(productTag.getProduct().getId())
                                        .name(product.getName())
                                        .originFileName(product.getProImgList().get(0).getOrigFileName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleProductTagApiResponse;
                            }).collect(Collectors.toList());

                    StyleHitListApiResponse styleHitListApiResponse = StyleHitListApiResponse.builder()
                            .userid(styleCustomer.getProfileName())
                            .userImg(customer.getImage())
                            .styleCnt(styleImgRepository.countByStyleId(style.getId()))
                            .content(style.getContent())
                            .styleId(style.getId())
                            .replyCnt(styleReplyRepository.countByStyleId(style.getId()))
                            .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                            .styleProductTagApiResponseList(styleProductTagApiResponseList)
                            .styleImg(style.getStyleImgList().get(0).getOrigFileName())
                            .hitBoolean(styleLikeService.liked(sessionId, style.getId()))
                            .hit(style.getHit())
                            .build();

                    return styleHitListApiResponse;
                }).sorted(Comparator.comparingLong(StyleHitListApiResponse::getHit).reversed())
                .collect(Collectors.toList());

        return Header.OK(styleListApiResponseHitList, stringList);
    }

    public Header<List<StyleHitListApiResponse>> noStyleHitList() {
        List<Style> styleList = baseRepository.findAll();
        List<String> stringList = hashTagRepository.HashTagNameTop5();

        List<StyleHitListApiResponse> styleListApiResponseHitList = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                            .map(styleHashTag -> {
                                StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                                        .tagName(styleHashTag.getHashTag().getTagName())
                                        .build();
                                return styleHashTagNameApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleProductTagApiResponse> styleProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleProductTagApiResponse styleProductTagApiResponse = StyleProductTagApiResponse.builder()
                                        .productId(productTag.getProduct().getId())
                                        .name(product.getName())
                                        .originFileName(product.getProImgList().get(0).getOrigFileName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleProductTagApiResponse;
                            }).collect(Collectors.toList());

                    StyleHitListApiResponse styleHitListApiResponse = StyleHitListApiResponse.builder()
                            .userid(styleCustomer.getProfileName())
                            .userImg(customer.getImage())
                            .styleCnt(styleImgRepository.countByStyleId(style.getId()))
                            .content(style.getContent())
                            .styleId(style.getId())
                            .replyCnt(styleReplyRepository.countByStyleId(style.getId()))
                            .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                            .styleProductTagApiResponseList(styleProductTagApiResponseList)
                            .styleImg(style.getStyleImgList().get(0).getOrigFileName())
                            .hitBoolean(false)
                            .hit(style.getHit())
                            .build();

                    return styleHitListApiResponse;
                }).sorted(Comparator.comparingLong(StyleHitListApiResponse::getHit).reversed())
                .collect(Collectors.toList());

        return Header.OK(styleListApiResponseHitList,stringList);
    }

    // 유저정보
    public Header<StyleUserInfoApiResponse> styleUserList(Long customerId, Long sessionId) {
        Customer customer1 = customerRepository.getById(customerId);
        List<Style> styleList = customer1.getStyleList();

        List<StyleUserListApiResponse> styleUserListApiResponseList = styleList.stream()
                .map(style -> {

                    List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                            .map(styleHashTag -> {
                                StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                                        .tagName(styleHashTag.getHashTag().getTagName())
                                        .build();
                                return styleHashTagNameApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleProductTagApiResponse> styleProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleProductTagApiResponse styleProductTagApiResponse = StyleProductTagApiResponse.builder()
                                        .productId(productTag.getProduct().getId())
                                        .name(product.getName())
                                        .originFileName(product.getProImgList().get(0).getOrigFileName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleProductTagApiResponse;
                            }).collect(Collectors.toList());

                    StyleUserListApiResponse styleUserListApiResponse = StyleUserListApiResponse.builder()
                            .styleId(style.getId())
                            .content(style.getContent())
                            .replyCnt(styleReplyRepository.countByStyleId(style.getId()))
                            .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                            .styleProductTagApiResponseList(styleProductTagApiResponseList)
                            .styleImg(style.getStyleImgList().get(0).getOrigFileName())
                            .hitBoolean(styleLikeService.liked(sessionId, style.getId()))
                            .styleCnt(styleImgRepository.countByStyleId(style.getId()))
                            .hit(style.getHit())
                            .regdate(style.getRegdate())
                            .build();
                    return styleUserListApiResponse;

                }).sorted(Comparator.comparing(StyleUserListApiResponse::getRegdate).reversed())
                .collect(Collectors.toList());

        List<Follow> followerList = customer1.getFollowerList();
        List<FollowingListApiResponse> followingListApiResponseList = followerList.stream()
                .map(follow -> {
                    FollowingListApiResponse followingListApiResponse = FollowingListApiResponse.builder()
                            .id(follow.getFollowing().getId())
                            .profileName(follow.getFollowing().getStyleCustomerList().get(0).getProfileName())
                            .name(follow.getFollowing().getStyleCustomerList().get(0).getName())
                            .originFileName(follow.getFollowing().getImage())
                            .followingBoolean(followRepository.existsByFollowingIdAndFollowerId(follow.getFollowing().getId(), sessionId))
                            .build();
                    return followingListApiResponse;
                }).collect(Collectors.toList());

        List<Follow> followingList = customer1.getFollowingList();
        List<FollowerListApiResponse> followerListApiResponseList = followingList.stream()
                .map(follow -> {
                    FollowerListApiResponse followerListApiResponse = FollowerListApiResponse.builder()
                            .id(follow.getFollower().getId())
                            .profileName(follow.getFollower().getStyleCustomerList().get(0).getProfileName())
                            .name(follow.getFollower().getStyleCustomerList().get(0).getName())
                            .originFileName(follow.getFollower().getImage())
                            .followerBoolean(followRepository.existsByFollowingIdAndFollowerId(follow.getFollower().getId(), sessionId))
                            .build();
                    return followerListApiResponse;
                }).collect(Collectors.toList());

        String intro = customer1.getStyleCustomerList().get(0).getIntro();
        if(intro == null){
            intro = "정보없음";
        }

        StyleUserInfoApiResponse styleUserInfoApiResponse = StyleUserInfoApiResponse.builder()
                .userid(customer1.getStyleCustomerList().get(0).getProfileName())
                .name(customer1.getStyleCustomerList().get(0).getName())
                .userImg(customer1.getImage())
                .intro(intro)
                .followerListApiResponseList(followerListApiResponseList)
                .followBoolean(followService.linked(customerId,sessionId))
                .followerCnt(followRepository.countByFollowingId(customerId))
                .followingListApiResponseList(followingListApiResponseList)
                .followingCnt(followRepository.countByFollowerId(customerId))
                .boardCnt(styleRepository.countByCustomerId(customerId))
                .styleUserListApiResponseList(styleUserListApiResponseList)
                .build();


        return Header.OK(styleUserInfoApiResponse);
    }

    public Header<StyleUserInfoApiResponse> noStyleUserList(Long customerId) {
        Customer customer1 = customerRepository.getById(customerId);
        List<Style> styleList = customer1.getStyleList();

        List<StyleUserListApiResponse> styleUserListApiResponseList = styleList.stream()
                .map(style -> {

                    List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                            .map(styleHashTag -> {
                                StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                                        .tagName(styleHashTag.getHashTag().getTagName())
                                        .build();
                                return styleHashTagNameApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleProductTagApiResponse> styleProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleProductTagApiResponse styleProductTagApiResponse = StyleProductTagApiResponse.builder()
                                        .productId(productTag.getProduct().getId())
                                        .name(product.getName())
                                        .originFileName(product.getProImgList().get(0).getOrigFileName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleProductTagApiResponse;
                            }).collect(Collectors.toList());

                    StyleUserListApiResponse styleUserListApiResponse = StyleUserListApiResponse.builder()
                            .styleId(style.getId())
                            .content(style.getContent())
                            .replyCnt(styleReplyRepository.countByStyleId(style.getId()))
                            .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                            .styleProductTagApiResponseList(styleProductTagApiResponseList)
                            .styleImg(style.getStyleImgList().get(0).getOrigFileName())
                            .hitBoolean(false)
                            .styleCnt(styleImgRepository.countByStyleId(style.getId()))
                            .hit(style.getHit())
                            .regdate(style.getRegdate())
                            .build();
                    return styleUserListApiResponse;

                }).sorted(Comparator.comparing(StyleUserListApiResponse::getRegdate).reversed())
                .collect(Collectors.toList());

        List<Follow> followerList = customer1.getFollowerList();
        List<FollowingListApiResponse> followingListApiResponseList = followerList.stream()
                .map(follow -> {
                    FollowingListApiResponse followingListApiResponse = FollowingListApiResponse.builder()
                            .id(follow.getFollowing().getId())
                            .profileName(follow.getFollowing().getStyleCustomerList().get(0).getProfileName())
                            .name(follow.getFollowing().getStyleCustomerList().get(0).getName())
                            .originFileName(follow.getFollowing().getImage())
                            .followingBoolean(false)
                            .build();
                    return followingListApiResponse;
                }).collect(Collectors.toList());

        List<Follow> followingList = customer1.getFollowingList();
        List<FollowerListApiResponse> followerListApiResponseList = followingList.stream()
                .map(follow -> {
                    FollowerListApiResponse followerListApiResponse = FollowerListApiResponse.builder()
                            .id(follow.getFollower().getId())
                            .profileName(follow.getFollower().getStyleCustomerList().get(0).getProfileName())
                            .name(follow.getFollower().getStyleCustomerList().get(0).getName())
                            .originFileName(follow.getFollower().getImage())
                            .followerBoolean(false)
                            .build();
                    return followerListApiResponse;
                }).collect(Collectors.toList());

        String intro = customer1.getStyleCustomerList().get(0).getIntro();
        if(intro == null){
            intro = "정보없음";
        }

        StyleUserInfoApiResponse styleUserInfoApiResponse = StyleUserInfoApiResponse.builder()
                .userid(customer1.getStyleCustomerList().get(0).getProfileName())
                .name(customer1.getStyleCustomerList().get(0).getName())
                .userImg(customer1.getImage())
                .intro(intro)
                .followerListApiResponseList(followerListApiResponseList)
                .followBoolean(false)
                .followerCnt(followRepository.countByFollowingId(customerId))
                .followingListApiResponseList(followingListApiResponseList)
                .followingCnt(followRepository.countByFollowerId(customerId))
                .boardCnt(styleRepository.countByCustomerId(customerId))
                .styleUserListApiResponseList(styleUserListApiResponseList)
                .build();


        return Header.OK(styleUserInfoApiResponse);
    }

    public Header<List<StyleHashListApiResponse>> styleHashList(String tagName, Long sessionId) {
        List<Style> styleList = styleRepository.findAllBy(tagName);
        List<StyleHashListApiResponse> styleHashListApiResponseList = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                            .map(styleHashTag -> {
                                StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                                        .tagName(styleHashTag.getHashTag().getTagName())
                                        .build();
                                return styleHashTagNameApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleProductTagApiResponse> styleProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleProductTagApiResponse styleProductTagApiResponse = StyleProductTagApiResponse.builder()
                                        .productId(productTag.getProduct().getId())
                                        .name(product.getName())
                                        .originFileName(product.getProImgList().get(0).getOrigFileName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleProductTagApiResponse;
                            }).collect(Collectors.toList());

                    StyleHashListApiResponse styleHashListApiResponse = StyleHashListApiResponse.builder()
                            .userid(styleCustomer.getProfileName())
                            .styleId(style.getId())
                            .userImg(customer.getImage())
                            .styleCnt(styleImgRepository.countByStyleId(style.getId()))
                            .content(style.getContent())
                            .replyCnt(styleReplyRepository.countByStyleId(style.getId()))
                            .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                            .styleProductTagApiResponseList(styleProductTagApiResponseList)
                            .styleImg(style.getStyleImgList().get(0).getOrigFileName())
                            .hit(style.getHit())
                            .regdate(style.getRegdate())
                            .hitBoolean(styleLikeService.liked(sessionId, style.getId()))
                            .build();
                    return styleHashListApiResponse;

                }).sorted(Comparator.comparing(StyleHashListApiResponse::getRegdate).reversed())
                .collect(Collectors.toList());
        return Header.OK(styleHashListApiResponseList);
    }

    public Header<List<StyleHashListApiResponse>> noStyleHashList(String tagName) {
        List<Style> styleList = styleRepository.findAllBy(tagName);
        List<StyleHashListApiResponse> styleHashListApiResponseList = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = style.getStyleHashTagList().stream()
                            .map(styleHashTag -> {
                                StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                                        .tagName(styleHashTag.getHashTag().getTagName())
                                        .build();
                                return styleHashTagNameApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleProductTagApiResponse> styleProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleProductTagApiResponse styleProductTagApiResponse = StyleProductTagApiResponse.builder()
                                        .productId(productTag.getProduct().getId())
                                        .name(product.getName())
                                        .originFileName(product.getProImgList().get(0).getOrigFileName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleProductTagApiResponse;
                            }).collect(Collectors.toList());

                    StyleHashListApiResponse styleHashListApiResponse = StyleHashListApiResponse.builder()
                            .userid(styleCustomer.getProfileName())
                            .styleId(style.getId())
                            .userImg(customer.getImage())
                            .styleCnt(styleImgRepository.countByStyleId(style.getId()))
                            .content(style.getContent())
                            .replyCnt(styleReplyRepository.countByStyleId(style.getId()))
                            .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                            .styleProductTagApiResponseList(styleProductTagApiResponseList)
                            .styleImg(style.getStyleImgList().get(0).getOrigFileName())
                            .hit(style.getHit())
                            .regdate(style.getRegdate())
                            .hitBoolean(false)
                            .build();
                    return styleHashListApiResponse;

                }).sorted(Comparator.comparing(StyleHashListApiResponse::getRegdate).reversed())
                .collect(Collectors.toList());
        return Header.OK(styleHashListApiResponseList);
    }

    public Header<List<StyleDetailListApiResponse>> detailList(Long sessionId){
        List<Style> styleList = baseRepository.findAll();
        List<StyleDetailListApiResponse> styleDetailListApiResponses = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleImgListApiResponse> styleImgListApiResponseList = style.getStyleImgList().stream()
                            .map(styleImg -> {
                                StyleImgListApiResponse styleImgListApiResponse = StyleImgListApiResponse.builder()
                                        .origFileName(styleImg.getOrigFileName())
                                        .build();
                                return styleImgListApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailProductTagApiResponse> styleDetailProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleDetailProductTagApiResponse styleDetailProductTagApiResponse = StyleDetailProductTagApiResponse.builder()
                                        .id(product.getId())
                                        .origFileName(product.getProImgList().get(0).getOrigFileName())
                                        .name(product.getName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleDetailProductTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailHashTagApiResponse> styleDetailHashTagApiResponseList = style.getStyleHashTagList().stream()
                            .map(hashTag -> {
                                StyleDetailHashTagApiResponse styleDetailHashTagApiResponse = StyleDetailHashTagApiResponse.builder()
                                        .tagName(hashTag.getHashTag().getTagName())
                                        .build();
                                return styleDetailHashTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleReply> styleReplyList = styleReplyRepository.ReplyList(style.getId());
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
                                        .replyBoolean(replyLikeService.liked(sessionId, styleReply.getId()))
                                        .regdate(styleReply.getRegdate())
                                        .build();
                                return styleReplyDetailApiResponse;
                            }).collect(Collectors.toList());

                    StyleDetailListApiResponse styleDetailListApiResponse = StyleDetailListApiResponse.builder()
                            .styleId(style.getId())
                            .customerId(customer.getId())
                            .customerUserId(styleCustomer.getProfileName())
                            .customerOriginFile(customer.getImage())
                            .hit(style.getHit())
                            .content(style.getContent())
                            .styleImgListApiResponseList(styleImgListApiResponseList)
                            .styleDetailProductTagApiResponseList(styleDetailProductTagApiResponseList)
                            .styleDetailHashTagApiResponseList(styleDetailHashTagApiResponseList)
                            .styleReplyDetailApiResponseList(styleReplyDetailApiResponseList)
                            .hitBoolean(styleLikeService.liked(sessionId, style.getId()))
                            .followBoolean(followService.linked(customer.getId(),sessionId))
                            .regdate(style.getRegdate())
                            .build();
                    return styleDetailListApiResponse;

                }).collect(Collectors.toList());
        return Header.OK(styleDetailListApiResponses);
    }

    public Header<List<StyleDetailListApiResponse>> noDetailList(){
        List<Style> styleList = baseRepository.findAll();
        List<StyleDetailListApiResponse> styleDetailListApiResponses = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleImgListApiResponse> styleImgListApiResponseList = style.getStyleImgList().stream()
                            .map(styleImg -> {
                                StyleImgListApiResponse styleImgListApiResponse = StyleImgListApiResponse.builder()
                                        .origFileName(styleImg.getOrigFileName())
                                        .build();
                                return styleImgListApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailProductTagApiResponse> styleDetailProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleDetailProductTagApiResponse styleDetailProductTagApiResponse = StyleDetailProductTagApiResponse.builder()
                                        .id(product.getId())
                                        .origFileName(product.getProImgList().get(0).getOrigFileName())
                                        .name(product.getName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleDetailProductTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailHashTagApiResponse> styleDetailHashTagApiResponseList = style.getStyleHashTagList().stream()
                            .map(hashTag -> {
                                StyleDetailHashTagApiResponse styleDetailHashTagApiResponse = StyleDetailHashTagApiResponse.builder()
                                        .tagName(hashTag.getHashTag().getTagName())
                                        .build();
                                return styleDetailHashTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleReply> styleReplyList = styleReplyRepository.ReplyList(style.getId());
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
                                        .replyBoolean(false)
                                        .regdate(styleReply.getRegdate())
                                        .build();
                                return styleReplyDetailApiResponse;
                            }).collect(Collectors.toList());

                    StyleDetailListApiResponse styleDetailListApiResponse = StyleDetailListApiResponse.builder()
                            .styleId(style.getId())
                            .customerId(customer.getId())
                            .customerUserId(styleCustomer.getProfileName())
                            .customerOriginFile(customer.getImage())
                            .hit(style.getHit())
                            .content(style.getContent())
                            .styleImgListApiResponseList(styleImgListApiResponseList)
                            .styleDetailProductTagApiResponseList(styleDetailProductTagApiResponseList)
                            .styleDetailHashTagApiResponseList(styleDetailHashTagApiResponseList)
                            .styleReplyDetailApiResponseList(styleReplyDetailApiResponseList)
                            .hitBoolean(false)
                            .followBoolean(false)
                            .regdate(style.getRegdate())
                            .build();
                    return styleDetailListApiResponse;

                }).collect(Collectors.toList());
        return Header.OK(styleDetailListApiResponses);
    }

    public Header<List<StyleDetailListApiResponse>> detailFollowingList(Long followerId){
        List<Style> styleList = styleRepository.findAllByFollowerId(followerId);
        List<StyleDetailListApiResponse> styleDetailListApiResponses = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleImgListApiResponse> styleImgListApiResponseList = style.getStyleImgList().stream()
                            .map(styleImg -> {
                                StyleImgListApiResponse styleImgListApiResponse = StyleImgListApiResponse.builder()
                                        .origFileName(styleImg.getOrigFileName())
                                        .build();
                                return styleImgListApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailProductTagApiResponse> styleDetailProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleDetailProductTagApiResponse styleDetailProductTagApiResponse = StyleDetailProductTagApiResponse.builder()
                                        .id(product.getId())
                                        .origFileName(product.getProImgList().get(0).getOrigFileName())
                                        .name(product.getName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleDetailProductTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailHashTagApiResponse> styleDetailHashTagApiResponseList = style.getStyleHashTagList().stream()
                            .map(hashTag -> {
                                StyleDetailHashTagApiResponse styleDetailHashTagApiResponse = StyleDetailHashTagApiResponse.builder()
                                        .tagName(hashTag.getHashTag().getTagName())
                                        .build();
                                return styleDetailHashTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleReply> styleReplyList = styleReplyRepository.ReplyList(style.getId());
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
                                        .replyBoolean(replyLikeService.liked(followerId, styleReply.getId()))
                                        .regdate(styleReply.getRegdate())
                                        .build();
                                return styleReplyDetailApiResponse;
                            }).collect(Collectors.toList());

                    StyleDetailListApiResponse styleDetailListApiResponse = StyleDetailListApiResponse.builder()
                            .styleId(style.getId())
                            .customerId(customer.getId())
                            .customerUserId(styleCustomer.getProfileName())
                            .customerOriginFile(customer.getImage())
                            .hit(style.getHit())
                            .content(style.getContent())
                            .styleImgListApiResponseList(styleImgListApiResponseList)
                            .styleDetailProductTagApiResponseList(styleDetailProductTagApiResponseList)
                            .styleDetailHashTagApiResponseList(styleDetailHashTagApiResponseList)
                            .styleReplyDetailApiResponseList(styleReplyDetailApiResponseList)
                            .hitBoolean(styleLikeService.liked(followerId, style.getId()))
                            .followBoolean(followService.linked(followerId, customer.getId()))
                            .regdate(style.getRegdate())
                            .build();
                    return styleDetailListApiResponse;

                }).collect(Collectors.toList());
        return Header.OK(styleDetailListApiResponses);
    }



    public Header<List<StyleDetailListApiResponse>> detailUserList(Long customerId, Long sessionId){
        List<Style> styleList = styleRepository.findAllByCustomerId(customerId);
        List<StyleDetailListApiResponse> styleDetailListApiResponses = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleImgListApiResponse> styleImgListApiResponseList = style.getStyleImgList().stream()
                            .map(styleImg -> {
                                StyleImgListApiResponse styleImgListApiResponse = StyleImgListApiResponse.builder()
                                        .origFileName(styleImg.getOrigFileName())
                                        .build();
                                return styleImgListApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailProductTagApiResponse> styleDetailProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleDetailProductTagApiResponse styleDetailProductTagApiResponse = StyleDetailProductTagApiResponse.builder()
                                        .id(product.getId())
                                        .origFileName(product.getProImgList().get(0).getOrigFileName())
                                        .name(product.getName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleDetailProductTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailHashTagApiResponse> styleDetailHashTagApiResponseList = style.getStyleHashTagList().stream()
                            .map(hashTag -> {
                                StyleDetailHashTagApiResponse styleDetailHashTagApiResponse = StyleDetailHashTagApiResponse.builder()
                                        .tagName(hashTag.getHashTag().getTagName())
                                        .build();
                                return styleDetailHashTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleReply> styleReplyList = styleReplyRepository.ReplyList(style.getId());
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
                                        .replyBoolean(replyLikeService.liked(sessionId, styleReply.getId()))
                                        .regdate(styleReply.getRegdate())
                                        .build();
                                return styleReplyDetailApiResponse;
                            }).collect(Collectors.toList());

                    StyleDetailListApiResponse styleDetailListApiResponse = StyleDetailListApiResponse.builder()
                            .styleId(style.getId())
                            .customerId(customer.getId())
                            .customerUserId(styleCustomer.getProfileName())
                            .customerOriginFile(customer.getImage())
                            .hit(style.getHit())
                            .content(style.getContent())
                            .styleImgListApiResponseList(styleImgListApiResponseList)
                            .styleDetailProductTagApiResponseList(styleDetailProductTagApiResponseList)
                            .styleDetailHashTagApiResponseList(styleDetailHashTagApiResponseList)
                            .styleReplyDetailApiResponseList(styleReplyDetailApiResponseList)
                            .hitBoolean(styleLikeService.liked(sessionId, style.getId()))
                            .followBoolean(followService.linked(sessionId, customer.getId()))
                            .regdate(style.getRegdate())
                            .build();
                    return styleDetailListApiResponse;

                }).collect(Collectors.toList());
        return Header.OK(styleDetailListApiResponses);
    }

    public Header<List<StyleDetailListApiResponse>> noDetailUserList(Long customerId){
        List<Style> styleList = styleRepository.findAllByCustomerId(customerId);
        List<StyleDetailListApiResponse> styleDetailListApiResponses = styleList.stream()
                .map(style -> {
                    Customer customer = style.getCustomer();
                    StyleCustomer styleCustomer = customer.getStyleCustomerList().get(0);
                    List<StyleImgListApiResponse> styleImgListApiResponseList = style.getStyleImgList().stream()
                            .map(styleImg -> {
                                StyleImgListApiResponse styleImgListApiResponse = StyleImgListApiResponse.builder()
                                        .origFileName(styleImg.getOrigFileName())
                                        .build();
                                return styleImgListApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailProductTagApiResponse> styleDetailProductTagApiResponseList = style.getProductTagList().stream()
                            .map(productTag -> {
                                Product product = productTag.getProduct();
                                StyleDetailProductTagApiResponse styleDetailProductTagApiResponse = StyleDetailProductTagApiResponse.builder()
                                        .id(product.getId())
                                        .origFileName(product.getProImgList().get(0).getOrigFileName())
                                        .name(product.getName())
                                        .price(salesRepository.findByProductId(product.getId()))
                                        .build();
                                return styleDetailProductTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleDetailHashTagApiResponse> styleDetailHashTagApiResponseList = style.getStyleHashTagList().stream()
                            .map(hashTag -> {
                                StyleDetailHashTagApiResponse styleDetailHashTagApiResponse = StyleDetailHashTagApiResponse.builder()
                                        .tagName(hashTag.getHashTag().getTagName())
                                        .build();
                                return styleDetailHashTagApiResponse;
                            }).collect(Collectors.toList());

                    List<StyleReply> styleReplyList = styleReplyRepository.ReplyList(style.getId());
                    List<StyleReplyDetailApiResponse> styleReplyDetailApiResponseList = styleReplyList.stream()
                            .map(styleReply -> {
                                Customer customer1 = styleReply.getCustomer();
                                StyleCustomer styleCustomer1 = customer.getStyleCustomerList().get(0);


                                StyleReplyDetailApiResponse styleReplyDetailApiResponse = StyleReplyDetailApiResponse.builder()
                                        .id(styleReply.getId())
                                        .customerId(customer1.getId())
                                        .customerUserid(styleCustomer1.getProfileName())
                                        .customerOriginFile(customer1.getImage())
                                        .content(styleReply.getContent())
                                        .depth(styleReply.getDepth())
                                        .hit(styleReply.getHit())
                                        .replyBoolean(false)
                                        .regdate(styleReply.getRegdate())
                                        .build();
                                return styleReplyDetailApiResponse;
                            }).collect(Collectors.toList());

                    StyleDetailListApiResponse styleDetailListApiResponse = StyleDetailListApiResponse.builder()
                            .styleId(style.getId())
                            .customerId(customer.getId())
                            .customerUserId(styleCustomer.getProfileName())
                            .customerOriginFile(customer.getImage())
                            .hit(style.getHit())
                            .content(style.getContent())
                            .styleImgListApiResponseList(styleImgListApiResponseList)
                            .styleDetailProductTagApiResponseList(styleDetailProductTagApiResponseList)
                            .styleDetailHashTagApiResponseList(styleDetailHashTagApiResponseList)
                            .styleReplyDetailApiResponseList(styleReplyDetailApiResponseList)
                            .hitBoolean(false)
                            .followBoolean(false)
                            .regdate(style.getRegdate())
                            .build();
                    return styleDetailListApiResponse;

                }).collect(Collectors.toList());
        return Header.OK(styleDetailListApiResponses);
    }

    public Header delete(Long id){
        Optional<Style> optionalStyle = baseRepository.findById(id);

        return optionalStyle.map(style -> {
            baseRepository.delete(style);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public StyleApiResponse response(Style style){
        StyleApiResponse styleApiResponse = StyleApiResponse.builder()
                .id(style.getId())
                .content(style.getContent())
                .customerId(style.getCustomer().getId())
                .build();
        return styleApiResponse;
    }

    public Header<List<StyleApiResponse>> search(Pageable pageable){
        Page<Style> styles = baseRepository.findAll(pageable);
        List<StyleApiResponse> styleApiResponseList = styles.stream()
                .map(pro -> response(pro))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(styles.getTotalPages())
                .totalElements(styles.getTotalElements())
                .currentPage(styles.getNumber())
                .build();
        return Header.OK(styleApiResponseList, pagination);
    }

    // 스타일 detail
    public Header<StyleDetailApiResponse> detail(Long styleId){
        Style style = baseRepository.getById(styleId);

        List<StyleImg> styleImgList = style.getStyleImgList();
        List<StyleImgListApiResponse> styleImgListApiResponseList = styleImgList.stream()
                .map(styleImg -> {
                    StyleImgListApiResponse styleImgListApiResponse = StyleImgListApiResponse.builder()
                            .origFileName(styleImg.getOrigFileName())
                            .build();
                    return styleImgListApiResponse;
                }).collect(Collectors.toList());

        List<ProductTag> productTagList = style.getProductTagList();
        List<StyleProductTagIdApiResponse> styleProductTagIdApiResponseList = productTagList.stream()
                .map(productTag -> {
                    StyleProductTagIdApiResponse styleProductTagIdApiResponse = StyleProductTagIdApiResponse.builder()
                            .productId(productTag.getProduct().getId())
                            .originFileName(productTag.getProduct().getProImgList().get(0).getOrigFileName())
                            .name(productTag.getProduct().getName())
                            .build();
                    return styleProductTagIdApiResponse;
                }).collect(Collectors.toList());

        List<StyleHashTag> hashTagList = style.getStyleHashTagList();
        List<StyleHashTagNameApiResponse> styleHashTagNameApiResponseList = hashTagList.stream()
                .map(styleHashTag -> {
                    StyleHashTagNameApiResponse styleHashTagNameApiResponse = StyleHashTagNameApiResponse.builder()
                            .tagName(styleHashTag.getHashTag().getTagName())
                            .build();
                    return styleHashTagNameApiResponse;
                }).collect(Collectors.toList());

        StyleDetailApiResponse styleDetailApiResponse = StyleDetailApiResponse.builder()
                .id(style.getId())
                .content(style.getContent())
                .styleImgListApiResponseList(styleImgListApiResponseList)
                .styleProductTagIdApiResponseList(styleProductTagIdApiResponseList)
                .styleHashTagNameApiResponseList(styleHashTagNameApiResponseList)
                .build();
        return Header.OK(styleDetailApiResponse);
    }
}