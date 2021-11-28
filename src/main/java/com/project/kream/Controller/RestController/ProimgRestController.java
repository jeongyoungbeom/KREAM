package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.ProImg;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProimgApiRequest;
import com.project.kream.Model.response.ProimgApiResponse;
import com.project.kream.Service.ProimgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProimgRestController extends CrudController<ProimgApiRequest, ProimgApiResponse, ProImg> {
    private final ProimgService proimgService;

    // 이미지 등록
    @PostMapping("/api/img_register")
    public Header<ProimgApiResponse> create(Header<ProimgApiRequest> request) {
        return null;
    }

    // 이미지 수정
    @PutMapping("/api/img_update")
    public Header<ProimgApiResponse> update(Header<ProimgApiRequest> request) {
        return proimgService.update(request);
    }

}
