package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.CardInfo;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.CardInfoApiRequest;
import com.project.kream.Model.response.CardInfoApiResponse;
import com.project.kream.Service.CardInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardInfoRestController extends CrudController<CardInfoApiRequest, CardInfoApiResponse, CardInfo> {
    private final CardInfoService cardInfoService;

    // 카드 등록
    @PostMapping("/api/cardInfo_register")
    public Header<CardInfoApiResponse> create(@RequestBody Header<CardInfoApiRequest> request) {
        return cardInfoService.create(request);
    }
    // 카드 플래그 수정
    @PutMapping("/api/cardInfo_flag_update")
    public Header<CardInfoApiResponse> flagUpdate(@RequestBody Header<CardInfoApiRequest> request) {
        return cardInfoService.flagUpdate(request);
    }
    // 카드 리스트(회원아이디로 조회)
    @GetMapping("/api/cardInfo_list/{id}")
    public Header<List<CardInfoApiResponse>> List(@PathVariable Long id){
        return cardInfoService.List(id);
    }

    // 카드 삭제
    @DeleteMapping("/api/cardInfo_delete/{id}")
    public Header<CardInfoApiResponse> delete(@PathVariable Long id){
        return cardInfoService.delete(id);
    }


}
