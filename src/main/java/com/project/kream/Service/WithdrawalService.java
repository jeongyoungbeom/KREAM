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
public class WithdrawalService extends BaseService<WithdrawalApiRequest, WithdrawalApiResponse, Withdrawal> {
    private final WithdrawalRepository withdrawalRepository;
    private final WithdrawalSpecification withdrawalSpecification;

    public Header<WithdrawalApiResponse> create(Header<WithdrawalApiRequest> request) {
        WithdrawalApiRequest withdrawalApiRequest = request.getData();
        Withdrawal withdrawal = Withdrawal.builder()
                .email(withdrawalApiRequest.getEmail())
                .hp(withdrawalApiRequest.getHp())
                .build();
        Withdrawal newWithdrawal = baseRepository.save(withdrawal);
        return Header.OK(response(newWithdrawal));
    }

//    public Header<List<WithdrawalApiResponse>> List(Pageable pageable){
//        Page<Withdrawal> withdrawalList = baseRepository.findAll(pageable);
//        List<WithdrawalApiResponse> withdrawalApiResponses = withdrawalList.stream()
//                .map(withdrawal -> {
//                    WithdrawalApiResponse withdrawalApiResponse = WithdrawalApiResponse.builder()
//                            .id(withdrawal.getId())
//                            .email(withdrawal.getEmail())
//                            .hp(withdrawal.getHp())
//                            .regdate(withdrawal.getRegdate())
//                            .build();
//                    return withdrawalApiResponse;
//                }).collect(Collectors.toList());
//        Pagination pagination = Pagination.builder()
//                .totalPages(withdrawalList.getTotalPages())
//                .totalElements(withdrawalList.getTotalElements())
//                .currentPage(withdrawalList.getNumber())
//                .currentElements(withdrawalList.getNumberOfElements())
//                .build();
//        return Header.OK(withdrawalApiResponses, pagination);
//    }

    public Header<List<WithdrawalApiResponse>> List(Header<WithdrawalApiRequest> request, Pageable pageable){
        WithdrawalApiRequest withdrawalApiRequest = request.getData();
        Page<Withdrawal> withdrawalList = withdrawalRepository.WithdrawalSearch(withdrawalApiRequest.getEmail(), withdrawalApiRequest.getHp(), withdrawalApiRequest.getStartDatetime(), withdrawalApiRequest.getEndDatetime(), pageable);
        List<WithdrawalApiResponse> withdrawalApiResponseList = withdrawalList.stream()
                .map(withdrawal -> {
                    WithdrawalApiResponse withdrawalApiResponse = WithdrawalApiResponse.builder()
                            .id(withdrawal.getId())
                            .email(withdrawal.getEmail())
                            .hp(withdrawal.getHp())
                            .regdate(withdrawal.getRegdate())
                            .build();
                    return withdrawalApiResponse;
                }).collect(Collectors.toList());
        int countPage = 5;
        int startPage = ((withdrawalList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > withdrawalList.getTotalPages()) {
            endPage = withdrawalList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(withdrawalList.getTotalPages())
                .totalElements(withdrawalList.getTotalElements())
                .currentPage(withdrawalList.getNumber())
                .currentElements(withdrawalList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return Header.OK(withdrawalApiResponseList, pagination);
    }

    public Header<List<WithdrawalApiResponse>> dataList(Header<WithdrawalApiRequest> request, Pageable pageable){
        Page<Withdrawal> withdrawalList = withdrawalSpecification.searchWithdrawalList(request, pageable);

        List<WithdrawalApiResponse> withdrawalApiResponseList = withdrawalList.stream()
                .map(withdrawal -> {
                    WithdrawalApiResponse withdrawalApiResponse = WithdrawalApiResponse.builder()
                            .email(withdrawal.getEmail())
                            .hp(withdrawal.getHp())
                            .regdate(withdrawal.getRegdate())
                            .build();
                    return withdrawalApiResponse;
                }).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((withdrawalList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > withdrawalList.getTotalPages()) {
            endPage = withdrawalList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(withdrawalList.getTotalPages())
                .totalElements(withdrawalList.getTotalElements())
                .currentPage(withdrawalList.getNumber())
                .currentElements(withdrawalList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();

        return Header.OK(withdrawalApiResponseList, pagination);

    }



    public WithdrawalApiResponse response(Withdrawal withdrawal){
        WithdrawalApiResponse withdrawalApiResponse = WithdrawalApiResponse.builder()
                .id(withdrawal.getId())
                .email(withdrawal.getEmail())
                .hp(withdrawal.getHp())
                .build();
        return withdrawalApiResponse;
    }
}

