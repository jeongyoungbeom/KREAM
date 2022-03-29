package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.response.ProductTagApiResponse;
import com.project.kream.Service.ProductTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductTagRestController {
    private final ProductTagService productTagService;

    // 전체 게시글의 상품 태그 리스트
    @GetMapping("/api/protag_list")
    public Header<List<ProductTagApiResponse>> list(){
        return productTagService.list();
    }




}
