package com.project.kream.Controller.RestController;


import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.ProSize;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProsizeApiRequest;
import com.project.kream.Model.response.ProsizeApiResponse;
import com.project.kream.Service.ProsizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProsizeRestController extends CrudController<ProsizeApiRequest, ProsizeApiResponse, ProSize> {
    private final ProsizeService prosizeService;

    @PostMapping("/api/prosize_register") //제품 사이즈 등록
    public Header<ProsizeApiResponse> create(@RequestBody Header<ProsizeApiRequest> request) {
        return prosizeService.create(request);
    }

    @PutMapping("/api/prosize_update")
    public Header<ProsizeApiResponse> update(@RequestBody Header<ProsizeApiRequest> request) {
        return prosizeService.update(request);
    }

    @DeleteMapping("/api/prosize_delete/{id}")
    public Header<ProsizeApiResponse> delete(@PathVariable Long id){
        return prosizeService.delete(id);
    }

    @GetMapping("/api/prosize_list") // 체결내역 리스트
    public Header<List<ProsizeApiResponse>> list(){
        return prosizeService.list();
    }



}
