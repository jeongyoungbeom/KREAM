package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.ProductTag;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProductTagApiRequest;
import com.project.kream.Model.response.ProductTagApiResponse;
import com.project.kream.Service.ProductTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductTagRestController extends CrudController<ProductTagApiRequest, ProductTagApiResponse, ProductTag> {
    private final ProductTagService productTagService;

    //게시글 상품태그 등록
    @PostMapping("/api/protag_register")
    public Header<ProductTagApiResponse> create(@RequestBody Header<ProductTagApiRequest> request) {
        return null;
    }

    @PutMapping("/api/protag_update")
    public Header<ProductTagApiResponse> update(Header<ProductTagApiRequest> request) {
        return null;
    }

    // 전체 게시글의 상품 태그 리스트
    @GetMapping("/api/protag_list")
    public Header<List<ProductTagApiResponse>> list(){
        return productTagService.list();
    }




}
