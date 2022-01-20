package com.project.kream.Controller.RestController;


import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.SalesApiRequest;
import com.project.kream.Model.response.SalesAdminApiResponse;
import com.project.kream.Model.response.SalesApiResponse;
import com.project.kream.Model.response.SalesListApiResponse;
import com.project.kream.Model.response.SalesUserApiResponse;
import com.project.kream.Service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalesRestController extends CrudController<SalesApiRequest, SalesApiResponse, Sales> {
    private final SalesService salesService;

    //판매 입찰 등록
    @PostMapping("/api/sales_register")
    public Header<Long> create(@RequestBody Header<SalesApiRequest> request) {
            return salesService.create(request);
            }

    //판매입찰 ID로 해당 입찰내역 업데이트(id는 내부 데이터로 확인)
    @PutMapping("/api/sales_update")
    public Header<Long> update(@RequestBody Header<SalesApiRequest> request) {
            return salesService.update(request);
            }

    //모든 판매입찰 내역 조회
    @PostMapping("/api/sales_list")
    public Header<List<SalesListApiResponse>> List(@RequestBody Header<SalesApiRequest> request, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
            return salesService.List(request, pageable);
    }

    //판매 입찰 ID로 상세 내용 조회
    @GetMapping("/api/sales_detail/{id}")
    public Header<SalesApiResponse> salesdetail(@PathVariable(name="id") Long id) {
        return salesService.detail(id);
    }

    //해당 id의 판매입찰 데이터 삭제
    @DeleteMapping("/api/sales_delete/{id}")
    public Header<SalesApiResponse> delete(@PathVariable Long id){
            return salesService.delete(id);
            }

    // 판매내역 상세
    @GetMapping("/api/sales_detailInfo/{id}")
    public Header<SalesUserApiResponse> detailInfo(@PathVariable Long id){
        return salesService.detailInfo(id);
    }

    //구매내역 id에 대한 사용자 결제 정보(카드,계좌)
    @GetMapping("/api/sales_info/{id}")
    public Header<SalesAdminApiResponse> salesinfo(@PathVariable(name="id") Long id) {
        return salesService.salesinfo(id);
    }


}
