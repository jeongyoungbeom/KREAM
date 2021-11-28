package com.project.kream.Service;

import com.project.kream.Model.Entity.Account;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.AccountApiRequest;
import com.project.kream.Model.response.AccountApiResponse;
import com.project.kream.Repository.AccountRepository;
import com.project.kream.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService extends BaseService<AccountApiRequest, AccountApiResponse, Account> {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public Header<AccountApiResponse> create(Header<AccountApiRequest> request) {
        AccountApiRequest accountApiRequest = request.getData();
        Account account = Account.builder()
                .name(accountApiRequest.getName())
                .accountNumber(accountApiRequest.getAccountNumber())
                .bank(accountApiRequest.getBank())
                .customer(customerRepository.getById(accountApiRequest.getCustomerId()))
                .build();
        Account newAccount = baseRepository.save(account);
        return Header.OK(response(newAccount));
    }

    public Header<AccountApiResponse> update(Header<AccountApiRequest> request) {
        AccountApiRequest accountApiRequest = request.getData();
        Optional<Account> optionalAccount = baseRepository.findById(accountApiRequest.getId());
        return optionalAccount.map(account -> {
            account.setBank(accountApiRequest.getBank());
            account.setAccountNumber(accountApiRequest.getAccountNumber());
            account.setName(accountApiRequest.getName());
            return account;
        }).map(account -> baseRepository.save(account))
                .map(account -> response(account))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
    }

    // customerId 로 찾기
    public Header<AccountApiResponse> read(Long id){
        Optional<Account> account = accountRepository.findByCustomerId(id);
        Account newAccount = account.orElse(null);
        AccountApiResponse accountApiResponse = AccountApiResponse.builder()
                .customerId(newAccount.getCustomer().getId())
                .accountNumber(newAccount.getAccountNumber())
                .bank(newAccount.getBank())
                .customerId(newAccount.getCustomer().getId())
                .name(newAccount.getName())
                .id(newAccount.getId())
                .build();
        return Header.OK(accountApiResponse);
    }

    public Header<List<AccountApiResponse>> List(){
        List<Account> accountList = baseRepository.findAll();
        List<AccountApiResponse> accountApiResponseList = accountList.stream()
                .map(account -> response(account))
                .collect(Collectors.toList());
        return Header.OK(accountApiResponseList);
    }

    public AccountApiResponse response(Account account){
        AccountApiResponse accountApiResponse = AccountApiResponse.builder()
                .id(account.getId())
                .bank(account.getBank())
                .accountNumber(account.getAccountNumber())
                .name(account.getName())
                .build();
        return accountApiResponse;
    }
}
