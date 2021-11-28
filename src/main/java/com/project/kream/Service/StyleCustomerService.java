package com.project.kream.Service;

import com.project.kream.Model.Entity.StyleCustomer;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleCustomerApiRequest;
import com.project.kream.Model.response.StyleCustomerApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.StyleCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StyleCustomerService extends BaseService<StyleCustomerApiRequest, StyleCustomerApiResponse, StyleCustomer> {
    private final CustomerRepository customerRepository;
    private final StyleCustomerRepository styleCustomerRepository;

    public void create(Long customerId, String name){
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        StyleCustomer styleCustomer = StyleCustomer.builder()
                .customer(customerRepository.getById(customerId))
                .profileName(generatedString)
                .name(name)
                .build();
        baseRepository.save(styleCustomer);
    }

    public Header<StyleCustomerApiResponse> update(Header<StyleCustomerApiRequest> request){
        StyleCustomerApiRequest styleCustomerApiRequest = request.getData();
        Optional<StyleCustomer> styleCustomer = baseRepository.findById(styleCustomerApiRequest.getId());

        return styleCustomer.map(newStyleCustomer -> {
            newStyleCustomer.setProfileName(styleCustomerApiRequest.getProfileName());
            newStyleCustomer.setName(styleCustomerApiRequest.getName());
            newStyleCustomer.setIntro(styleCustomerApiRequest.getIntro());

            return newStyleCustomer;

        }).map(newStyleCustomer -> baseRepository.save(newStyleCustomer))
                .map(newStyleCustomer -> response(newStyleCustomer))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
    }

    public Header<StyleCustomerApiResponse> detail(Long customerId){

        StyleCustomer styleCustomer = styleCustomerRepository.findByCustomerId(customerId);
        StyleCustomerApiResponse styleCustomerApiResponse = StyleCustomerApiResponse.builder()
                .id(styleCustomer.getId())
                .customerId(styleCustomer.getCustomer().getId())
                .profileName(styleCustomer.getProfileName())
                .name(styleCustomer.getName())
                .intro(styleCustomer.getIntro())
                .build();
        return Header.OK(styleCustomerApiResponse);
    }
    public StyleCustomerApiResponse response(StyleCustomer styleCustomer){
        String intro = styleCustomer.getIntro();
        if(intro == null){
            intro = "정보없음";
        }
        StyleCustomerApiResponse styleCustomerApiResponse = StyleCustomerApiResponse.builder()
                .id(styleCustomer.getId())
                .customerId(styleCustomer.getCustomer().getId())
                .profileName(styleCustomer.getProfileName())
                .name(styleCustomer.getName())
                .intro(intro)
                .build();
        return styleCustomerApiResponse;
    }
}
