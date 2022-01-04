package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.PurchaseApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseRestController extends CrudController<PurchaseApiRequest, PurchaseApiResponse, Purchase> {
    private final PurchaseService purchaseService;

    //구매입찰 등록
    @PostMapping("/api/purchase_register")
    public Long create(@RequestBody Header<PurchaseApiRequest> request) {
        return purchaseService.create(request);
    }

    //구매입찰 수정
    @PutMapping("/api/purchase_update")
    public Long update(@RequestBody Header<PurchaseApiRequest> request) {
        return purchaseService.update(request);
    }

    //전체 구매입찰 리스트
    @PostMapping("/api/purchase_list")
    public Header<List<PurchaseListApiResponse>> List(@RequestBody Header<PurchaseApiRequest> request, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return purchaseService.List(request, pageable);
    }

    // 미결제
    @GetMapping("/api/purchase_nopayList")
    public Header<List<PurchaseNopayApiResponse>> noPayList(@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return purchaseService.purchaseList(pageable);
    }

    //해당 id의 구매입찰 데이터만 조회
    @GetMapping("/api/purchase_info/{id}")
    public Header<PurchaseAdminApiResponse> purchaseinfo(@PathVariable(name="id") Long id) {
        return purchaseService.purchaseinfo(id);
    }

    // 구매내역 상세
    @GetMapping("/api/purchase_detail/{id}")
    public Header<PurchaseUserApiResponse> detailInfo(@PathVariable Long id){
        return purchaseService.detailInfo(id);
    }

    //해당 id의 구매입찰 데이터 삭제
    @DeleteMapping("/api/purchase_delete/{id}")
    public int delete(@PathVariable Long id){
        return purchaseService.delete(id);
    }

}

