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
public class CardInfoService {
    private final CustomerRepository customerRepository;
    private final CardInfoRepository cardInfoRepository;

    public Long create(Header<CardInfoApiRequest> request) {
        CardInfoApiRequest cardInfoApiRequest = request.getData();
        System.out.println(request);
        CardInfo cardInfo = cardInfoRepository.save(cardInfoApiRequest.toEntity(customerRepository.getById(cardInfoApiRequest.getCustomerId())));
        return cardInfo.getId();
    }

    public Header<List<CardInfoApiResponse>> List(Long id){
        List<CardInfo> cardInfoList = cardInfoRepository.findAllByCustomerId(id);
        List<CardInfoApiResponse> cardInfoApiResponseList = cardInfoList.stream()
                .map(CardInfoApiResponse::new)
                .collect(Collectors.toList());
        return Header.OK(cardInfoApiResponseList);
    }

    public int delete(Long id){
        Optional<CardInfo> optionalCardInfo = cardInfoRepository.findById(id);
        if(optionalCardInfo.isPresent()){
            cardInfoRepository.delete(optionalCardInfo.get());
            return 1;
        }
        return 0;
    }

    // 기본 카드 수정
    public Long flagUpdate(Header<CardInfoApiRequest> request) {
        CardInfoApiRequest cardInfoApiRequest = request.getData();
        System.out.println(request);
        CardInfo cardInfo = cardInfoRepository.findById(cardInfoApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("카드 정보 없음"));
        cardInfo.update(cardInfoApiRequest.getCardCompany(), cardInfo.getCardNumber(),cardInfo.getExpiration(),cardInfo.getBirthdate(),cardInfo.getCardpw(),cardInfo.getCardFlag(),cardInfo.getCustomer());
        return cardInfoApiRequest.getId();

    }

}
