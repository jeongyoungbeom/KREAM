package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.request.WithdrawalApiRequest;
import com.project.kream.Model.response.WithdrawalApiResponse;
import com.project.kream.Service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WithdrawalRestController {
    private final WithdrawalService withdrawalService;

    //관리자 리스트
    @PostMapping("/api/with_list")
    public Header<List<WithdrawalApiResponse>> List(@RequestBody Header<WithdrawalApiRequest> request, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return withdrawalService.List(request, pageable);
    }

    //조건 검색
    @PostMapping("/api/with_dataList")
    public Header<List<WithdrawalApiResponse>> dataList(@RequestBody Header<WithdrawalApiRequest> request,@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return withdrawalService.dataList(request, pageable);
    }

}
