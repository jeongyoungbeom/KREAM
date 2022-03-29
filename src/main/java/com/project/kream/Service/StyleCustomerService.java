package com.project.kream.Service;

import com.project.kream.Model.Entity.StyleCustomer;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleCustomerApiRequest;
import com.project.kream.Model.response.StyleCustomerApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.StyleCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StyleCustomerService {
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

        StyleCustomerApiRequest styleCustomerApiRequest = new StyleCustomerApiRequest();
        styleCustomerRepository.save(styleCustomerApiRequest.toEntity(
                customerRepository.getById(customerId),
                generatedString,
                name
        ));
    }

    @Transactional
    public Header<Long> update(Header<StyleCustomerApiRequest> request){
        StyleCustomerApiRequest styleCustomerApiRequest = request.getData();
        StyleCustomer styleCustomer = styleCustomerRepository.findById(styleCustomerApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
        styleCustomer.update(styleCustomerApiRequest.getProfileName(), styleCustomerApiRequest.getName(), styleCustomerApiRequest.getIntro());

        return Header.OK(styleCustomerApiRequest.getId());
    }

    public Header<StyleCustomerApiResponse> detail(Long customerId){
        StyleCustomer styleCustomer = styleCustomerRepository.findByCustomerId(customerId);
        return Header.OK(new StyleCustomerApiResponse(styleCustomer));
    }

}
