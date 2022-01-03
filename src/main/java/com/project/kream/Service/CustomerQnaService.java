package com.project.kream.Service;

import com.project.kream.Model.Entity.CustomerQna;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.CustomerQnaApiRequest;
import com.project.kream.Model.response.CustomerQnaApiResponse;
import com.project.kream.Repository.CustomerQnaRepository;
import com.project.kream.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerQnaService extends BaseService<CustomerQnaApiRequest, CustomerQnaApiResponse, CustomerQna>{
    private final CustomerRepository customerRepository;
    private final CustomerQnaRepository customerQnaRepository;

    public Long create(Header<CustomerQnaApiRequest> request) {
        System.out.println(request);
        CustomerQnaApiRequest customerQnaApiRequest = request.getData();
        CustomerQna customerQna = customerQnaRepository.save(customerQnaApiRequest.toEntity(customerRepository.getById(customerQnaApiRequest.getCustomerId())));
        return customerQna.getId();
    }

    public Long update(Header<CustomerQnaApiRequest> request) {
        CustomerQnaApiRequest customerQnaApiRequest = request.getData();
        CustomerQna customerQna = customerQnaRepository.getById(customerQnaApiRequest.getId());
        customerQna.update(customerQna.getStatus(),customerQna.getType(),customerQna.getTitle(),customerQna.getContent(),customerQna.getAnswer(),customerQna.getAcomment(),customerQna.getCustomer());
        return customerQna.getId();
    }

    public Header<CustomerQnaApiResponse> read(Long id){
        CustomerQna customerQna = customerQnaRepository.getById(id);
        return Header.OK(new CustomerQnaApiResponse(customerQna));
    }


    public Header<List<CustomerQnaApiResponse>> List(Pageable pageable){
        Page<CustomerQna> customerQnaList = baseRepository.findAll(pageable);
        List<CustomerQnaApiResponse> customerQnaApiResponseList = customerQnaList.stream()
                .map(CustomerQnaApiResponse::new)
                .collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((customerQnaList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > customerQnaList.getTotalPages()) {
            endPage = customerQnaList.getTotalPages();
        }
        return Header.OK(customerQnaApiResponseList, new Pagination(customerQnaList, startPage, endPage));
    }

    public int delete(Long id){
        Optional<CustomerQna> optionalQnA = baseRepository.findById(id);
        if(optionalQnA.isPresent()){
            customerQnaRepository.delete(optionalQnA.get());
            return 1;
        }
        return 0;
    }
}