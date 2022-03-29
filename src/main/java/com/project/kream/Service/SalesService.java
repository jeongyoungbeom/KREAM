package com.project.kream.Service;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.request.SalesApiRequest;
import com.project.kream.Model.request.TransactionApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    private final TransactionRepository transactionRepository;

    public Header<Long> create(Header<SalesApiRequest> request) {
        SalesApiRequest salesApiRequest = request.getData();
        Sales newsellEntity;
        if(salesApiRequest.getPurchaseId()==0) {
            newsellEntity = salesRepository.save(salesApiRequest.toEntity(
                    productRepository.getById(salesApiRequest.getProductId()),
                    customerRepository.getById(salesApiRequest.getCustomerId()),
                    addressRepository.getById(salesApiRequest.getAddressId()),
                    accountRepository.getById(salesApiRequest.getAccountId())
            ));
        }else{
            newsellEntity = salesRepository.save(salesApiRequest.toEntity(
                    productRepository.getById(salesApiRequest.getProductId()),
                    customerRepository.getById(salesApiRequest.getCustomerId()),
                    addressRepository.getById(salesApiRequest.getAddressId()),
                    accountRepository.getById(salesApiRequest.getAccountId()),
                    purchaseRepository.getById(salesApiRequest.getPurchaseId())
            ));

            purchaseRepository.findByProductIdAndId(newsellEntity.getId(), PurchaseStatus1.진행중, PurchaseStatus2.입고대기, salesApiRequest.getPurchaseId());

            if(salesApiRequest.getStatus1().equals(SalesStatus1.진행중)){
                TransactionApiRequest transactionApiRequest = TransactionApiRequest.builder()
                        .sizeType(salesApiRequest.getSizeType())
                        .price(salesApiRequest.getPrice())
                        .build();
                transactionRepository.save(transactionApiRequest.toEntity(productRepository.getById(salesApiRequest.getProductId())));
            }
        }
        return Header.OK(newsellEntity.getId());
    }

    @Transactional
    public Header<Long> update(Header<SalesApiRequest> request) {
        SalesApiRequest salesApiRequest = request.getData();
        Sales sales = salesRepository.findById(salesApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
        sales.update(salesApiRequest.getPrice(), salesApiRequest.getPeriod(), salesApiRequest.getSizeType(), salesApiRequest.getStatus1(), salesApiRequest.getStatus2(), productRepository.getById(salesApiRequest.getProductId()), customerRepository.getById(salesApiRequest.getCustomerId()));
        return Header.OK(salesApiRequest.getId());
    }

    public Header<List<SalesListApiResponse>> List(Header<SalesApiRequest> request, Pageable pageable){
        SalesApiRequest salesApiRequest = request.getData();
        Page<Sales> salesEntities = salesRepository.SearchList(salesApiRequest.getId(), salesApiRequest.getStatus2(), salesApiRequest.getStatus3(), salesApiRequest.getProductId(), salesApiRequest.getRegdate1(), salesApiRequest.getRegdate2(), pageable);
        List<SalesListApiResponse> salesListApiResponseList = salesEntities.stream()
                .map(sales -> new SalesListApiResponse(sales.getProduct().getId(), sales)).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((salesEntities.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > salesEntities.getTotalPages()) {
            endPage = salesEntities.getTotalPages();
        }

        return Header.OK(salesListApiResponseList, new Pagination(salesEntities, startPage, endPage));
    }

    public Header delete(Long id){
        Optional<Sales> sales = salesRepository.findById(id);
        return sales.map(sale -> {
            salesRepository.delete(sale);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<SalesApiResponse> detail(Long id) {
        Sales sales = salesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
        return Header.OK(new SalesApiResponse(sales));
    }

    // 관리자 판매 입찰 상세
    public Header<SalesAdminApiResponse> salesinfo(Long id){
        Sales sales = salesRepository.getById(id);
        return Header.OK(new SalesAdminApiResponse(sales.getCustomer(), sales, sales.getProduct(), sales.getAccount()));
    }

    // 사용자 판매내역 상세
    public Header<SalesUserApiResponse> detailInfo(Long id){
        Sales sales = salesRepository.getById(id);
        return Header.OK(new SalesUserApiResponse(
                sales,
                sales.getProduct(),
                sales.getAccount(),
                sales.getAddress(),
                salesRepository.findBySizeTypeAndProductIdAndStatus1(sales.getSizeType(), sales.getProduct().getId()),
                purchaseRepository.findBySizeTypeAndProductId(sales.getSizeType(), sales.getProduct().getId())
        ));
    }
}
