package com.project.kream.Service;

import com.project.kream.Model.Entity.Account;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.AccountApiRequest;
import com.project.kream.Model.response.AccountApiResponse;
import com.project.kream.Repository.AccountRepository;
import com.project.kream.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public Long create(Header<AccountApiRequest> request) {
        AccountApiRequest accountApiRequest = request.getData();
        Account account = accountRepository.save(accountApiRequest.toEntity(customerRepository.getById(accountApiRequest.getCustomerId())));
        return account.getId();
    }

    public Long update(Header<AccountApiRequest> request) {
        AccountApiRequest accountApiRequest = request.getData();
        Account account = accountRepository.getById(accountApiRequest.getId());

        account.update(accountApiRequest.getBank(), accountApiRequest.getAccountNumber(), account.getName());
        return account.getId();
    }

    // customerId 로 찾기
    public Header<AccountApiResponse> read(Long id){
        Account account = accountRepository.findByCustomerId(id).orElse(null);
        return Header.OK(new AccountApiResponse(account));
    }

}
