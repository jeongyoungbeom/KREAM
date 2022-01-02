package com.project.kream.Controller.RestController;


import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Header;
import com.project.kream.Model.enumclass.CustomerType;
import com.project.kream.Model.request.CustomerApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CustomerRestController extends CrudController<CustomerApiRequest, CustomerApiResponse, Customer> {
    private final CustomerService customerService;

    // 회원등록
    @PostMapping("/api/customer_register")
    public Header<Long> create(@RequestBody Header<CustomerApiRequest> request) {
        return customerService.create(request);
    }
    // 마이페이지
    @GetMapping("/api/customer_detail/{id}")
    public Header<CustomerApiResponse> read(@PathVariable Long id){
        return customerService.read(id);
    }

    // 로그인 세션
    @GetMapping("/api/customer_session/{email}")
    public Long session(@PathVariable String email){
        return customerService.session(email);
    }

    // 회원수정
    @PatchMapping("/api/customer_update")
    public Long update(@RequestBody Header<CustomerApiRequest> request) {
        return customerService.update(request);
    }

    // 비밀번호 체크
    @GetMapping("/api/customer_pwcheck/{id}/{userpw}")
    public Boolean pwcheck(@PathVariable Long id, @PathVariable String userpw){
        return customerService.pwCheck(id, userpw);
    }

    // 마이페이지
    @GetMapping("/api/customer_mypage/{id}")
    public Header<CustomerMypage1ApiResponse> mypage(@PathVariable Long id){
        return customerService.mypage(id);
    }


    // 구매내역
    @GetMapping("/api/customer_purchaseinfo/{id}/{regdate1}/{regdate2}")
    public Header<List<CustomerPurchaseInfoApiResponse>> purchaseinfo(@PathVariable Long id, @PathVariable String regdate1, @PathVariable String regdate2){
        return customerService.purchaseInfo(id, regdate1, regdate2);
    }

    // 판매내역
    @GetMapping("/api/customer_salesinfo/{id}/{regdate1}/{regdate2}")
    public Header<List<CustomerSalesInfoApiResponse>> salesinfo(@PathVariable Long id, @PathVariable String regdate1, @PathVariable String regdate2){
        return customerService.salesInfo(id, regdate1, regdate2);
    }

    // 회원 리스트
    @GetMapping("/api/customer_list/{customerType}")
    public Header<List<CustomerListApiResponse>> List(@PathVariable CustomerType customerType, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return customerService.List(customerType, pageable);
    }
    // 비밀번호 찾기
    @GetMapping("/api/customer_searchpw/{email}/{hp}")
    public void searchPW(@PathVariable String email, @PathVariable String hp){
        customerService.searchPW(email, hp);
    }

    // 이메일 찾기
    @GetMapping("/api/customer_searchEmail/{hp}")
    public StringBuffer searchEmail(@PathVariable String hp){
        return customerService.searchEmail(hp);
    }

    // 찜목록
    @GetMapping("/api/customer_cartinfo/{id}")
    public Header<List<CustomerCartInfoApiResponse>> cartinfo(@PathVariable Long id){
        return customerService.cartinfo(id);
    }

    // 관리자 회원목록 상세
    @GetMapping("/api/customer_info/{id}")
    public Header<CustomerInfoApiResponse> customerInfo(@PathVariable Long id){
        return customerService.customerInfo(id);
    }

    // 조건 검색
    @PostMapping("/api/customer_dataList")
    public Header<List<CustomerSearchApiResponse>> dataList(@RequestBody Header<CustomerApiRequest> request,@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return customerService.dataList(request, pageable);
    }

    // 회원 탈퇴
    @DeleteMapping("/api/customer_delete/{id}")
    public Header<CustomerApiResponse> delete(@PathVariable Long id){
        return customerService.delete(id);
    }


}
