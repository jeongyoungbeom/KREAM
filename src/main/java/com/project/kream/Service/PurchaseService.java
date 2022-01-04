package com.project.kream.Service;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.enumclass.SalesStatus2;
import com.project.kream.Model.request.PurchaseApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService extends BaseService<PurchaseApiRequest, PurchaseApiResponse, Purchase> {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final SalesRepository salesRepository;
    private final TransactionRepository transactionRepository;
    private final AddressRepository addressRepository;
    private final CardInfoRepository cardInfoRepository;


    public Long create(Header<PurchaseApiRequest> request) {
        System.out.println(request);
        PurchaseApiRequest purchaseApiRequest = request.getData();
        Purchase newpurchase = null;
        Long purcharseId = 0L;
        if(purchaseApiRequest.getSalasId() == null) {
            purcharseId = purchaseRepository.save(purchaseApiRequest.toEntity(productRepository.getById(purchaseApiRequest.getProductId()), customerRepository.getById(purchaseApiRequest.getCustomerId()), addressRepository.getById(purchaseApiRequest.getAddressId()),cardInfoRepository.getById(purchaseApiRequest.getCardInfo()))).getId();

        }else{
            // 즉시구매
            purcharseId = purchaseRepository.save(purchaseApiRequest.toEntityTo(productRepository.getById(purchaseApiRequest.getProductId()), customerRepository.getById(purchaseApiRequest.getCustomerId()), salesRepository.getById(purchaseApiRequest.getSalasId()), addressRepository.getById(purchaseApiRequest.getAddressId()),cardInfoRepository.getById(purchaseApiRequest.getCardInfo()))).getId();

            if(purchaseApiRequest.getStatus1().equals(PurchaseStatus1.진행중)){
                Transaction transaction = Transaction.builder()
                        .sizeType(purchaseApiRequest.getSizeType())
                        .product(productRepository.getById(purchaseApiRequest.getProductId()))
                        .price(purchaseApiRequest.getPrice())
                        .build();
                transactionRepository.save(transaction);
            }
        }
        return purcharseId;
    }

    public Long update(Header<PurchaseApiRequest> request) {
        PurchaseApiRequest purchaseApiRequest = request.getData();
        Purchase purchase = purchaseRepository.getById(purchaseApiRequest.getId());

        purchase.update(purchase.getPrice(), purchase.getPeriod(), purchase.getSizeType(), purchase.getStatus1(), purchase.getStatus2(), purchase.getStatus3(), purchase.getProduct(), purchase.getCustomer(), purchase.getDeliveryList(), purchase.getAddress(), purchase.getCardInfo(), purchase.getSales());
        return purchase.getId();
    }

    public Header<List<PurchaseListApiResponse>> List(Header<PurchaseApiRequest> request, Pageable pageable){
        PurchaseApiRequest purchaseApiRequest = request.getData();
        Page<Purchase> purchaseList = purchaseRepository.SearchList(purchaseApiRequest.getId(), purchaseApiRequest.getStatus2(), purchaseApiRequest.getStatus3(), purchaseApiRequest.getProductId(),purchaseApiRequest.getRegdate1(), purchaseApiRequest.getRegdate2(), pageable);
        List<PurchaseListApiResponse> purchaseListApiResponseList = purchaseList.stream()
                .map(PurchaseListApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((purchaseList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > purchaseList.getTotalPages()) {
            endPage = purchaseList.getTotalPages();
        }
        return Header.OK(purchaseListApiResponseList, new Pagination(purchaseList, startPage, endPage));
    }

    public int delete(Long id){
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if(purchase.isPresent()){
            purchaseRepository.delete(purchase.get());
            return 1;
        }
        return 0;
    }

    // 관리자 입찰현황
    public Header<PurchaseAdminApiResponse> purchaseinfo(Long id){
        Purchase purchase = purchaseRepository.getById(id);
        Customer customer = purchase.getCustomer();
        Product product = purchase.getProduct();
        CardInfo cardInfo = purchase.getCardInfo();

        return Header.OK(new PurchaseAdminApiResponse(purchase));
    }


    // 사용자 구매 상세
    public Header<PurchaseUserApiResponse> detailInfo(Long id){
        Purchase purchase = purchaseRepository.getById(id);

//        Delivery delivery = purchase.getDeliveryList().get(0);

        String devCompany;
        Long trackNum;

        if (purchase.getDeliveryList().isEmpty()) {
            devCompany = "정보없음";
            trackNum = 0L;
        }else{
            devCompany = purchase.getDeliveryList().get(0).getDevCompany();
            trackNum = purchase.getDeliveryList().get(0).getTrackNum();
        }

        Long salesPrice = salesRepository.findBySizeTypeAndProductId(purchase.getSizeType(), purchase.getProduct().getId());
        Long purchasePrice = purchaseRepository.findBySizeTypeAndProductId(purchase.getSizeType(), purchase.getProduct().getId());

        return Header.OK(new PurchaseUserApiResponse(purchase, devCompany, trackNum, salesPrice, purchasePrice));

    }

    // 미결제 확인
    public Header<List<PurchaseNopayApiResponse>> purchaseList(Pageable pageable){
        Page<Purchase> purchaseList = purchaseRepository.findAllByStatus2(PurchaseStatus2.미결제, pageable);
        List<PurchaseNopayApiResponse> purchaseNopayApiResponseList = purchaseList.stream()
                .map(PurchaseNopayApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((purchaseList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > purchaseList.getTotalPages()) {
            endPage = purchaseList.getTotalPages();
        }

        return Header.OK(purchaseNopayApiResponseList, new Pagination(purchaseList, startPage, endPage));
    }


}
