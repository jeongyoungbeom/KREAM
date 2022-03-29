package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.request.DeliveryApiRequest;
import com.project.kream.Model.response.DeliveryListApiResponse;
import com.project.kream.Model.response.DeliveryPurchaseApiResponse;
import com.project.kream.Service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryRestController {
    private final DeliveryService deliveryService;

    // 배송정보 등록
    @PostMapping("/api/dev_register")
    public Header<Long> create(@RequestBody Header<DeliveryApiRequest> request) {
        return deliveryService.create(request);
    }

    //배송정보 업데이트
    @PutMapping("/api/dev_update")
    public Long update(@RequestBody Header<DeliveryApiRequest> request) {
        System.out.println(request);
        return deliveryService.update(request);
    }

    //배송정보 전체 리스트
    @PostMapping("/api/dev_list")
    public Header<List<DeliveryListApiResponse>> List(@RequestBody Header<DeliveryApiRequest> request, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return deliveryService.list(request, pageable);
    }

    // 배송삭제
    @DeleteMapping("/api/dev_delete/{id}")
    public int delete(@PathVariable Long id){
        return deliveryService.delete(id);
    }

     // 관리자 배송현황 상세
    @GetMapping("/api/dev_delivery/{id}")
    public Header<DeliveryPurchaseApiResponse> deliveryInfo(@PathVariable Long id){
        return deliveryService.deliveryInfo(id);
    }


}
