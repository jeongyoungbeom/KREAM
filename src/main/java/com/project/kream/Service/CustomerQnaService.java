package com.project.kream.Service;

import com.project.kream.Model.Entity.CustomerQna;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.CustomerQnaApiRequest;
import com.project.kream.Model.response.CustomerQnaApiResponse;
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

    public Header<CustomerQnaApiResponse> create(Header<CustomerQnaApiRequest> request) {
        CustomerQnaApiRequest customerQnaApiRequest = request.getData();

        CustomerQna customerQna = CustomerQna.builder()
                .status(customerQnaApiRequest.getStatus())
                .type(customerQnaApiRequest.getType())
                .title(customerQnaApiRequest.getTitle())
                .content(customerQnaApiRequest.getContent())
                .customer(customerRepository.getById(customerQnaApiRequest.getCustomerId()))
                .build();
        CustomerQna newqna = baseRepository.save(customerQna);
        return Header.OK(response(newqna));
    }

    public Header<CustomerQnaApiResponse> update(Header<CustomerQnaApiRequest> request) {
        CustomerQnaApiRequest customerQnaApiRequest = request.getData();
        Optional<CustomerQna> optionalQnA = baseRepository.findById(customerQnaApiRequest.getId());
        return optionalQnA.map(customerQna -> {
                    customerQna.setStatus(customerQnaApiRequest.getStatus());
                    customerQna.setType(customerQnaApiRequest.getType());
                    customerQna.setTitle(customerQnaApiRequest.getTitle());
                    customerQna.setContent(customerQnaApiRequest.getContent());
                    customerQna.setAnswer(customerQnaApiRequest.getAnswer());
                    customerQna.setAcomment(customerQnaApiRequest.getAcomment());
                    return customerQna;
                }).map(customerQna -> baseRepository.save(customerQna))
                .map(customerQna -> response(customerQna))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터가 없습니다."));
    }

    public Header<CustomerQnaApiResponse> read(Long id){
        return baseRepository.findById(id)
                .map(customerQna -> response(customerQna))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터없음")
                );
    }


    public Header<List<CustomerQnaApiResponse>> List(Pageable pageable){
        Page<CustomerQna> customerQnaList = baseRepository.findAll(pageable);
        List<CustomerQnaApiResponse> customerQnaApiResponseList = customerQnaList.stream()
                .map(qna -> response(qna))
                .collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((customerQnaList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > customerQnaList.getTotalPages()) {
            endPage = customerQnaList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(customerQnaList.getTotalPages())
                .totalElements(customerQnaList.getTotalElements())
                .currentPage(customerQnaList.getNumber())
                .currentElements(customerQnaList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return Header.OK(customerQnaApiResponseList, pagination);
    }

    public Header delete(Long id){
        Optional<CustomerQna> optionalQnA = baseRepository.findById(id);
        return optionalQnA.map(qna ->{
            baseRepository.delete(qna);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터없음"));
    }


    public CustomerQnaApiResponse response(CustomerQna customerQna){
        CustomerQnaApiResponse customerQnaApiResponse = CustomerQnaApiResponse.builder()
                .id(customerQna.getId())
                .status(customerQna.getStatus())
                .type(customerQna.getType())
                .title(customerQna.getTitle())
                .content(customerQna.getContent())
                .customerId(customerQna.getCustomer().getId())
                .regdate(customerQna.getRegdate())
                .modifiedDate(customerQna.getIsmodified())
                .acomment(customerQna.getAcomment())
                .answer(customerQna.getAnswer())
                .build();
        return customerQnaApiResponse;
    }

}