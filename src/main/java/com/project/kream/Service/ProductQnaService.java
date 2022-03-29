package com.project.kream.Service;

import com.project.kream.Model.Entity.ProductQna;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.ProductQnaApiRequest;
import com.project.kream.Model.response.ProductQnaApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.ProductQnaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductQnaService {
    private final CustomerRepository customerRepository;
    private final ProductQnaRepository productQnaRepository;

    public Header<Long> create(Header<ProductQnaApiRequest> request) {
        ProductQnaApiRequest productQnaApiRequest = request.getData();
        ProductQna newqna = productQnaRepository.save(productQnaApiRequest.toEntity(customerRepository.getById(productQnaApiRequest.getCustomerId())));
        return Header.OK(newqna.getId());
    }

    @Transactional
    public Long update(Header<ProductQnaApiRequest> request) {
        ProductQnaApiRequest productQnaApiRequest = request.getData();
        ProductQna productQna = productQnaRepository.findById(productQnaApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
        productQna.update(productQnaApiRequest.getStatus(), productQnaApiRequest.getType(), productQnaApiRequest.getTitle(), productQnaApiRequest.getContent(), productQnaApiRequest.getAnswer(), productQnaApiRequest.getAcomment());
        return productQnaApiRequest.getId();
    }

    public Header<ProductQnaApiResponse> read(Long id){
        ProductQna productQna = productQnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
        return Header.OK(new ProductQnaApiResponse(productQna));
    }


    public Header<List<ProductQnaApiResponse>> List(Pageable pageable){
        Page<ProductQna> productQnaList = productQnaRepository.findAll(pageable);
        List<ProductQnaApiResponse> productQnaApiResponseList = productQnaList.stream()
                .map(ProductQnaApiResponse::new)
                .collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((productQnaList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > productQnaList.getTotalPages()) {
            endPage = productQnaList.getTotalPages();
        }
        return Header.OK(productQnaApiResponseList, new Pagination(productQnaList, startPage, endPage));
    }

    public int delete(Long id){
        Optional<ProductQna> optionalProductQna = productQnaRepository.findById(id);
        return optionalProductQna.map(qna ->{
            productQnaRepository.delete(qna);
            return 1;
        }).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
    }
}
