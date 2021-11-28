package com.project.kream.Service;

import com.project.kream.Model.Entity.ProductQna;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.ProductQnaApiRequest;
import com.project.kream.Model.response.ProductQnaApiResponse;
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
public class ProductQnaService extends BaseService<ProductQnaApiRequest, ProductQnaApiResponse, ProductQna>{
    private final CustomerRepository customerRepository;

    public Header<ProductQnaApiResponse> create(Header<ProductQnaApiRequest> request) {
        ProductQnaApiRequest productQnaApiRequest = request.getData();

        ProductQna qna = ProductQna.builder()
                .status(productQnaApiRequest.getStatus())
                .type(productQnaApiRequest.getType())
                .title(productQnaApiRequest.getTitle())
                .content(productQnaApiRequest.getContent())
                .customer(customerRepository.getById(productQnaApiRequest.getCustomerId()))
                .build();
        ProductQna newqna = baseRepository.save(qna);
        return Header.OK(response(newqna));
    }

    public Header<ProductQnaApiResponse> update(Header<ProductQnaApiRequest> request) {
        ProductQnaApiRequest productQnaApiRequest = request.getData();
        Optional<ProductQna> optionalProductQna = baseRepository.findById(productQnaApiRequest.getId());
        return optionalProductQna.map(productQna -> {
                    productQna.setStatus(productQnaApiRequest.getStatus());
                    productQna.setType(productQnaApiRequest.getType());
                    productQna.setTitle(productQnaApiRequest.getTitle());
                    productQna.setContent(productQnaApiRequest.getContent());
                    productQna.setAnswer(productQnaApiRequest.getAnswer());
                    productQna.setAcomment(productQnaApiRequest.getAcomment());
                    return productQna;
                }).map(productQna -> baseRepository.save(productQna))
                .map(productQna -> response(productQna))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터가 없습니다."));
    }

    public Header<ProductQnaApiResponse> read(Long id){
        return baseRepository.findById(id)
                .map(qna -> response(qna))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터없음")
                );
    }


    public Header<List<ProductQnaApiResponse>> List(Pageable pageable){
        Page<ProductQna> productQnaList = baseRepository.findAll(pageable);
        List<ProductQnaApiResponse> productQnaApiResponseList = productQnaList.stream()
                .map(qna -> response(qna))
                .collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((productQnaList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > productQnaList.getTotalPages()) {
            endPage = productQnaList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(productQnaList.getTotalPages())
                .totalElements(productQnaList.getTotalElements())
                .currentPage(productQnaList.getNumber())
                .currentElements(productQnaList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return Header.OK(productQnaApiResponseList, pagination);
    }

    public Header delete(Long id){
        Optional<ProductQna> optionalProductQna = baseRepository.findById(id);
        return optionalProductQna.map(qna ->{
            baseRepository.delete(qna);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터없음"));
    }


    public ProductQnaApiResponse response(ProductQna productQna){
        ProductQnaApiResponse productQnaApiResponse = ProductQnaApiResponse.builder()
                .id(productQna.getId())
                .status(productQna.getStatus())
                .type(productQna.getType())
                .title(productQna.getTitle())
                .content(productQna.getContent())
                .customerId(productQna.getCustomer().getId())
                .regdate(productQna.getRegdate())
                .modifiedDate(productQna.getIsmodified())
                .acomment(productQna.getAcomment())
                .answer(productQna.getAnswer())
                .build();
        return productQnaApiResponse;
    }
}
