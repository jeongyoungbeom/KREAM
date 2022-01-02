package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.Account;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.AccountApiRequest;
import com.project.kream.Model.response.AccountApiResponse;
import com.project.kream.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountRestController extends CrudController<AccountApiRequest, AccountApiResponse, Account> {
    private final AccountService accountService;

    // 계좌 등록
    @PostMapping("/api/account_register")
    public Long create(@RequestBody Header<AccountApiRequest> request) {
        return accountService.create(request);
    }

    // 계좌 수정
    @PutMapping("/api/account_update")
    public Long update(@RequestBody Header<AccountApiRequest> request) {
        return accountService.update(request);
    }

    // 계좌 상세내용
    @GetMapping("/api/account_detail/{id}")
    public Header<AccountApiResponse> read(@PathVariable Long id){
        return accountService.read(id);
    }

}
