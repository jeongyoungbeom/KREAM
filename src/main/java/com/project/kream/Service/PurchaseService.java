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


    public Header<PurchaseApiResponse> create(Header<PurchaseApiRequest> request) {
        PurchaseApiRequest purchaseApiRequest = request.getData();
        Purchase newpurchase = null;
        if(purchaseApiRequest.getSalasId() == 0) {
            Purchase purchaseEntity = Purchase.builder()
                    .product(productRepository.getById(purchaseApiRequest.getProductId()))
                    .customer(customerRepository.getById(purchaseApiRequest.getCustomerId()))
                    .price(purchaseApiRequest.getPrice())
                    .period(purchaseApiRequest.getPeriod())
                    .sizeType(purchaseApiRequest.getSizeType())
                    .status1(purchaseApiRequest.getStatus1())
                    .status2(purchaseApiRequest.getStatus2())
                    .status3(purchaseApiRequest.getStatus3())
                    .address(addressRepository.getById(purchaseApiRequest.getAddressId()))
                    .cardInfo(cardInfoRepository.getById(purchaseApiRequest.getCardId()))
                    .build();
            newpurchase = baseRepository.save(purchaseEntity);
        }else{
            Purchase purchaseEntity = Purchase.builder()
                    .product(productRepository.getById(purchaseApiRequest.getProductId()))
                    .customer(customerRepository.getById(purchaseApiRequest.getCustomerId()))
                    .sales(salesRepository.getById(purchaseApiRequest.getSalasId()))
                    .price(purchaseApiRequest.getPrice())
                    .period(purchaseApiRequest.getPeriod())
                    .sizeType(purchaseApiRequest.getSizeType())
                    .status1(purchaseApiRequest.getStatus1())
                    .status2(purchaseApiRequest.getStatus2())
                    .status3(purchaseApiRequest.getStatus3())
                    .address(addressRepository.getById(purchaseApiRequest.getAddressId()))
                    .cardInfo(cardInfoRepository.getById(purchaseApiRequest.getCardId()))
                    .build();

            newpurchase = baseRepository.save(purchaseEntity);
            salesRepository.findByProductIdAndId(newpurchase.getId(), SalesStatus1.진행중, SalesStatus2.발송요청, purchaseApiRequest.getSalasId());

            if(purchaseApiRequest.getStatus1().equals(PurchaseStatus1.진행중)){
                Transaction transaction = Transaction.builder()
                        .sizeType(purchaseApiRequest.getSizeType())
                        .product(productRepository.getById(purchaseApiRequest.getProductId()))
                        .price(purchaseApiRequest.getPrice())
                        .build();
                transactionRepository.save(transaction);
            }
        }

        return Header.OK(response(newpurchase));
    }

    public Header<PurchaseApiResponse> update(Header<PurchaseApiRequest> request) {
        PurchaseApiRequest purchaseApiRequest = request.getData();
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseApiRequest.getId());

        return purchase.map(buy -> {
                    buy.setProduct(productRepository.getById(purchaseApiRequest.getProductId()));
                    buy.setCustomer(customerRepository.getById(purchaseApiRequest.getCustomerId()));
                    buy.setPrice(purchaseApiRequest.getPrice());
                    buy.setPeriod(purchaseApiRequest.getPeriod());
                    buy.setSizeType(purchaseApiRequest.getSizeType());
                    buy.setStatus1(purchaseApiRequest.getStatus1());
                    buy.setStatus2(purchaseApiRequest.getStatus2());
                    buy.setStatus3(purchaseApiRequest.getStatus3());
                    return buy;
                }).map(buy -> baseRepository.save(buy))
                .map(buy -> response(buy))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
    }

    public Header<List<PurchaseListApiResponse>> List(Header<PurchaseApiRequest> request, Pageable pageable){
        PurchaseApiRequest purchaseApiRequest = request.getData();
        Page<Purchase> purchaseList = purchaseRepository.SearchList(purchaseApiRequest.getId(), purchaseApiRequest.getStatus2(), purchaseApiRequest.getStatus3(), purchaseApiRequest.getProductId(),purchaseApiRequest.getRegdate1(), purchaseApiRequest.getRegdate2(), pageable);
        List<PurchaseListApiResponse> purchaseListApiResponseList = purchaseList.stream()
                .map(purchase -> {
                    Product product = purchase.getProduct();
                    Address address = purchase.getAddress();

                    PurchaseListApiResponse purchaseListApiResponse = PurchaseListApiResponse.builder()
                            .productId(product.getId())
                            .purchaseId(purchase.getId())
                            .regdate(purchase.getRegdate())
                            .status3(purchase.getStatus3())
                            .status2(purchase.getStatus2())
                            .name(address.getName())
                            .build();

                    return purchaseListApiResponse;
                }).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((purchaseList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > purchaseList.getTotalPages()) {
            endPage = purchaseList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
//                .totalPages(purchaseList.getTotalPages())
//                .totalElements(purchaseList.getTotalElements())
//                .currentPage(purchaseList.getNumber())
//                .build();
                .totalPages(purchaseList.getTotalPages())
                .totalElements(purchaseList.getTotalElements())
                .currentPage(purchaseList.getNumber())
                .currentElements(purchaseList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return Header.OK(purchaseListApiResponseList,pagination);
    }

    public Header delete(Long id){
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.map(buy ->{
            baseRepository.delete(buy);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    public PurchaseApiResponse response(Purchase purchase){
        PurchaseApiResponse purchaseApiResponse = PurchaseApiResponse.builder()
                .id(purchase.getId())
                .productId(purchase.getProduct().getId())
                .customerId(purchase.getCustomer().getId())
                .price(purchase.getPrice())
                .period(purchase.getPeriod())
                .proimg(purchase.getProduct().getProImgList().get(0).getFilePath())
                .sizeType(purchase.getSizeType())
                .status1(purchase.getStatus1())
                .status2(purchase.getStatus2())
                .status3(purchase.getStatus3())
                .regdate(purchase.getRegdate())
                .build();
        return purchaseApiResponse;
    }

    // 관리자 입찰현황
    public Header<PurchaseAdminApiResponse> purchaseinfo(Long id){
        Purchase purchase = purchaseRepository.getById(id);
        Customer customer = purchase.getCustomer();
        Product product = purchase.getProduct();
        CardInfo cardInfo = purchase.getCardInfo();

        PurchaseAdminApiResponse purchaseAdminApiResponse = PurchaseAdminApiResponse.builder()
                .userid(customer.getUserid())
                .productId(product.getId())
                .productName(product.getKorName())
                .brand(product.getBrand())
                .size(purchase.getSizeType())
                .price(purchase.getPrice())
                .regdate(purchase.getRegdate())
                .email(customer.getEmail())
                .cardNumber(cardInfo.getCardNumber())
                .cardCompany(cardInfo.getCardCompany())
                .status3(purchase.getStatus3())
                .build();

        return Header.OK(purchaseAdminApiResponse);
    }


    // 사용자 구매 상세
    public Header<PurchaseUserApiResponse> detailInfo(Long id){
        Purchase purchase = purchaseRepository.getById(id);

        Delivery delivery = purchase.getDeliveryList().get(0);
        String devCompany;
        Long trackNum;

        if (delivery==null) {
            devCompany = "정보없음";
            trackNum = 0L;
        }else{
            devCompany = purchase.getDeliveryList().get(0).getDevCompany();
            trackNum = purchase.getDeliveryList().get(0).getTrackNum();
        }

        Long salesPrice = salesRepository.findBySizeTypeAndProductId(purchase.getSizeType(), purchase.getProduct().getId());

        Long purchasePrice = purchaseRepository.findBySizeTypeAndProductId(purchase.getSizeType(), purchase.getProduct().getId());

        PurchaseUserApiResponse purchaseUserApiResponse = PurchaseUserApiResponse.builder()
                .orderNumber(purchase.getId())
                .period(purchase.getPeriod())
                .status1(purchase.getStatus1())
                .status2(purchase.getStatus2())
                .productId(purchase.getProduct().getId())
                .productName(purchase.getProduct().getName())
                .size(purchase.getSizeType())
                .originFileName(purchase.getProduct().getProImgList().get(0).getOrigFileName())
                .price(purchase.getPrice())
                .regdate(purchase.getRegdate())
                .name(purchase.getAddress().getName())
                .hp(purchase.getAddress().getHp())
                .cardNumber(purchase.getCardInfo().getCardNumber())
                .cardCompany(purchase.getCardInfo().getCardCompany())
                .zipcode(purchase.getAddress().getZipcode())
                .address1(purchase.getAddress().getDetail1())
                .address2(purchase.getAddress().getDetail2())
                .devCompany(devCompany)
                .trackNum(trackNum)
                .salesPrice(salesPrice)
                .purchasePrice(purchasePrice)
                .build();
        return Header.OK(purchaseUserApiResponse);

    }

    // 미결제 확인
    public Header<List<PurchaseNopayApiResponse>> purchaseList(Pageable pageable){
        Page<Purchase> purchaseList = purchaseRepository.findAllByStatus2(PurchaseStatus2.미결제, pageable);
        List<PurchaseNopayApiResponse> purchaseNopayApiResponseList = purchaseList.stream()
                .map(purchase -> {
                    PurchaseNopayApiResponse purchaseNopayApiResponse = PurchaseNopayApiResponse.builder()
                            .id(purchase.getId())
                            .customerId(purchase.getCustomer().getId())
                            .productId(purchase.getProduct().getId())
                            .purchaseStatus2(purchase.getStatus2())
                            .userid(purchase.getCustomer().getUserid())
                            .name(purchase.getAddress().getName())
                            .regdate(purchase.getRegdate())
                            .build();
                    return purchaseNopayApiResponse;
                }).collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(purchaseList.getTotalPages())
                .totalElements(purchaseList.getTotalElements())
                .currentPage(purchaseList.getNumber())
                .build();

        return Header.OK(purchaseNopayApiResponseList, pagination);
    }


}
