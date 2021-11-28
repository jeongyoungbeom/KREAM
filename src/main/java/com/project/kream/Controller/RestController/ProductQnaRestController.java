package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.ProductQna;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProductQnaApiRequest;
import com.project.kream.Model.response.ProductQnaApiResponse;
import com.project.kream.Service.ProductQnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductQnaRestController extends CrudController<ProductQnaApiRequest, ProductQnaApiResponse, ProductQna> {
    private final ProductQnaService productQnaService;

    @PostMapping("/api/product_Qna_register")
    public Header<ProductQnaApiResponse> create(@RequestBody Header<ProductQnaApiRequest> request) {
        return productQnaService.create(request);
    }

    // 1:1 문의 상세내용
    @GetMapping("/api/product_Qna_detail/{id}")
    public Header<ProductQnaApiResponse> read(@PathVariable Long id){
        return productQnaService.read(id);
    }

    // 1:1 문의 수정
    @PutMapping("/api/product_Qna_update")
    public Header<ProductQnaApiResponse> update(@RequestBody Header<ProductQnaApiRequest> request) {
        return productQnaService.update(request);
    }

    // 1:1 문의 삭제
    @DeleteMapping("/api/product_Qna_delete/{id}")
    public Header<ProductQnaApiResponse> delete(@PathVariable Long id){
        return productQnaService.delete(id);
    }

    // 1:1 문의 리스트
    @GetMapping("/api/product_Qna_list")
    public Header<List<ProductQnaApiResponse>> List(@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return productQnaService.List(pageable);
    }
}

