package com.project.kream.Service;

import com.project.kream.Model.Entity.CardInfo;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.CardInfoApiRequest;
import com.project.kream.Model.response.CardInfoApiResponse;
import com.project.kream.Repository.CardInfoRepository;
import com.project.kream.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardInfoService extends BaseService<CardInfoApiRequest, CardInfoApiResponse, CardInfo>{
    private final CustomerRepository customerRepository;
    private final CardInfoRepository cardInfoRepository;

    public Header<CardInfoApiResponse> create(Header<CardInfoApiRequest> request) {
        CardInfoApiRequest cardInfoApiRequest = request.getData();

        CardInfo cardInfo = CardInfo.builder()
                .cardCompany(cardInfoApiRequest.getCardCompany())
                .cardNumber(cardInfoApiRequest.getCardNumber())
                .expiration(cardInfoApiRequest.getExpiration())
                .birthdate(cardInfoApiRequest.getBirthdate())
                .cardpw(cardInfoApiRequest.getCardpw())
                .cardFlag(cardInfoApiRequest.getCardFlag())
                .customer(customerRepository.getById(cardInfoApiRequest.getCustomerId()))
                .build();
        CardInfo newcardInfo = baseRepository.save(cardInfo);
        return Header.OK(response(newcardInfo));
    }

    public Header<CardInfoApiResponse> update(Header<CardInfoApiRequest> request) {
        return null;
    }


    public Header<List<CardInfoApiResponse>> List(Long id){
        List<CardInfo> cardInfoList = cardInfoRepository.findAllByCustomerId(id);
        List<CardInfoApiResponse> cardInfoApiResponseList = cardInfoList.stream()
                .map(cardInfo -> response(cardInfo))
                .collect(Collectors.toList());
        return Header.OK(cardInfoApiResponseList);
    }

    public Header delete(Long id){
        Optional<CardInfo> optionalCardInfo = baseRepository.findById(id);

        return optionalCardInfo.map(cardInfo -> {
            baseRepository.delete(cardInfo);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public CardInfoApiResponse response(CardInfo cardInfo){
        CardInfoApiResponse cardInfoApiResponse = CardInfoApiResponse.builder()
                .id(cardInfo.getId())
                .cardNumber(cardInfo.getCardNumber())
                .expiration(cardInfo.getExpiration())
                .birthdate(cardInfo.getBirthdate())
                .cardCompany(cardInfo.getCardCompany())
                .cardFlag(cardInfo.getCardFlag())
                .cardpw(cardInfo.getCardpw())
                .customerId(cardInfo.getCustomer().getId())
                .build();
        return cardInfoApiResponse;
    }
    // 기본 카드 수정
    public Header<CardInfoApiResponse> flagUpdate(Header<CardInfoApiRequest> request) {
        CardInfoApiRequest cardInfoApiRequest = request.getData();
        Optional<CardInfo> optionalCardInfo = baseRepository.findById(cardInfoApiRequest.getId());
        return optionalCardInfo.map(cardInfo ->{
                    cardInfo.setId(cardInfoApiRequest.getId());
                    cardInfo.setCardFlag(cardInfoApiRequest.getCardFlag());
                    return cardInfo;
                }).map(cardInfo -> baseRepository.save(cardInfo))
                .map(cardInfo -> response(cardInfo))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다다"));
    }

}
