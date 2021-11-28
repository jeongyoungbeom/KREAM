package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.CustomerQna;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.CustomerQnaApiRequest;
import com.project.kream.Model.response.CustomerQnaApiResponse;
import com.project.kream.Service.CustomerQnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerQnaRestController extends CrudController<CustomerQnaApiRequest, CustomerQnaApiResponse, CustomerQna> {
    private final CustomerQnaService customerQnaService;

    // 1:1 문의 등록
    @PostMapping("/api/customer_Qna_register")
    public Header<CustomerQnaApiResponse> create(@RequestBody Header<CustomerQnaApiRequest> request) {
        return customerQnaService.create(request);
    }

    // 1:1 문의 상세내용
    @GetMapping("/api/customer_Qna_detail/{id}")
    public Header<CustomerQnaApiResponse> read(@PathVariable Long id){
        return customerQnaService.read(id);
    }

    // 1:1 문의 수정
    @PutMapping("/api/customer_Qna_update")
    public Header<CustomerQnaApiResponse> update(@RequestBody Header<CustomerQnaApiRequest> request) {
        return customerQnaService.update(request);
    }

    // 1:1 문의 삭제
    @DeleteMapping("/api/customer_Qna_delete/{id}")
    public Header<CustomerQnaApiResponse> delete(@PathVariable Long id){
        return customerQnaService.delete(id);
    }

    // 1:1 문의 리스트
    @GetMapping("/api/customer_Qna_list")
    public Header<List<CustomerQnaApiResponse>> List(@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return customerQnaService.List(pageable);
    }
}
