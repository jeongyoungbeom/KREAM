package com.project.kream.Service;


import com.project.kream.Model.Entity.Withdrawal;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.WithdrawalApiRequest;
import com.project.kream.Model.response.WithdrawalApiResponse;
import com.project.kream.Repository.Specification.WithdrawalSpecification;
import com.project.kream.Repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WithdrawalService {
    private final WithdrawalRepository withdrawalRepository;
    private final WithdrawalSpecification withdrawalSpecification;

    // 탈퇴 회원 저장합니다.
    public Header<Long> create(String email, String hp) {
        WithdrawalApiRequest withdrawalApiRequest = WithdrawalApiRequest.builder()
                .email(email).hp(hp).build();
        Withdrawal withdrawal = withdrawalRepository.save(withdrawalApiRequest.toEntity());

        return Header.OK(withdrawal.getId());
    }

    public Header<List<WithdrawalApiResponse>> List(Pageable pageable){
        Page<Withdrawal> withdrawalList = withdrawalRepository.findAll(pageable);
        List<WithdrawalApiResponse> withdrawalApiResponses = withdrawalList.stream()
                .map(WithdrawalApiResponse::new).collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(withdrawalList.getTotalPages())
                .totalElements(withdrawalList.getTotalElements())
                .currentPage(withdrawalList.getNumber())
                .currentElements(withdrawalList.getNumberOfElements())
                .build();
        return Header.OK(withdrawalApiResponses, pagination);
    }

    // 관리자 리스트
    public Header<List<WithdrawalApiResponse>> List(Header<WithdrawalApiRequest> request, Pageable pageable){
        WithdrawalApiRequest withdrawalApiRequest = request.getData();
        Page<Withdrawal> withdrawalList = withdrawalRepository.WithdrawalSearch(withdrawalApiRequest.getEmail(), withdrawalApiRequest.getHp(), withdrawalApiRequest.getStartDatetime(), withdrawalApiRequest.getEndDatetime(), pageable);
        List<WithdrawalApiResponse> withdrawalApiResponseList = withdrawalList.stream()
                .map(WithdrawalApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((withdrawalList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > withdrawalList.getTotalPages()) {
            endPage = withdrawalList.getTotalPages();
        }

        return Header.OK(withdrawalApiResponseList, new Pagination(withdrawalList, startPage, endPage));
    }

    // 조건 검색
    public Header<List<WithdrawalApiResponse>> dataList(Header<WithdrawalApiRequest> request, Pageable pageable){
        Page<Withdrawal> withdrawalList = withdrawalSpecification.searchWithdrawalList(request, pageable);

        List<WithdrawalApiResponse> withdrawalApiResponseList = withdrawalList.stream()
                .map(WithdrawalApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((withdrawalList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > withdrawalList.getTotalPages()) {
            endPage = withdrawalList.getTotalPages();
        }


        return Header.OK(withdrawalApiResponseList, new Pagination(withdrawalList, startPage, endPage));

    }
}

