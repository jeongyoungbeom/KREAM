package com.project.kream.Service;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.PurchaseStatus2;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Model.request.SalesApiRequest;
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
public class SalesService extends BaseService<SalesApiRequest, SalesApiResponse, Sales> {
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    private final TransactionRepository transactionRepository;

    public Header<SalesApiResponse> create(Header<SalesApiRequest> request) {
        SalesApiRequest salesApiRequest = request.getData();
        Sales newsellEntity = null;
        if(salesApiRequest.getPurchaseId()==0) {
            Sales salesEntity = Sales.builder()
                    .product(productRepository.getById(salesApiRequest.getProductId()))
                    .customer(customerRepository.getById(salesApiRequest.getCustomerId()))
                    .address(addressRepository.getById(salesApiRequest.getAddressId()))
                    .account(accountRepository.getById(salesApiRequest.getAccountId()))
                    .price(salesApiRequest.getPrice())
                    .period(salesApiRequest.getPeriod())
                    .sizeType(salesApiRequest.getSizeType())
                    .status1(salesApiRequest.getStatus1())
                    .status2(salesApiRequest.getStatus2())
                    .status3(salesApiRequest.getStatus3())
                    .build();
            newsellEntity = baseRepository.save(salesEntity);
        }else{
            Sales salesEntity = Sales.builder()
                    .product(productRepository.getById(salesApiRequest.getProductId()))
                    .customer(customerRepository.getById(salesApiRequest.getCustomerId()))
                    .address(addressRepository.getById(salesApiRequest.getAddressId()))
                    .account(accountRepository.getById(salesApiRequest.getAccountId()))
                    .price(salesApiRequest.getPrice())
                    .period(salesApiRequest.getPeriod())
                    .sizeType(salesApiRequest.getSizeType())
                    .status1(salesApiRequest.getStatus1())
                    .status2(salesApiRequest.getStatus2())
                    .status3(salesApiRequest.getStatus3())
                    .purchase(purchaseRepository.getById(salesApiRequest.getPurchaseId()))
                    .build();
            newsellEntity = baseRepository.save(salesEntity);

            purchaseRepository.findByProductIdAndId(newsellEntity.getId(), PurchaseStatus1.진행중, PurchaseStatus2.입고대기, salesApiRequest.getPurchaseId());

            if(salesApiRequest.getStatus1().equals(SalesStatus1.진행중)){
                Transaction transaction = Transaction.builder()
                        .sizeType(salesApiRequest.getSizeType())
                        .product(productRepository.getById(salesApiRequest.getProductId()))
                        .price(salesApiRequest.getPrice())
                        .build();
                transactionRepository.save(transaction);
            }
        }
        return Header.OK(response(newsellEntity));
    }

    public Header<SalesApiResponse> update(Header<SalesApiRequest> request) {
        SalesApiRequest salesApiRequest = request.getData();
        Optional<Sales> optionalSales = salesRepository.findById(salesApiRequest.getId());

        return optionalSales.map(sales ->{
                    sales.setProduct(productRepository.getById(salesApiRequest.getProductId()));
                    sales.setCustomer(customerRepository.getById(salesApiRequest.getCustomerId()));
                    sales.setPrice(salesApiRequest.getPrice());
                    sales.setPeriod(salesApiRequest.getPeriod());
                    sales.setSizeType(salesApiRequest.getSizeType());
                    sales.setStatus1(salesApiRequest.getStatus1());
                    sales.setStatus2(salesApiRequest.getStatus2());
                    return sales;
                }).map(sell -> baseRepository.save(sell))
                .map(sell ->response(sell))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
    }

    public Header<List<SalesListApiResponse>> List(Header<SalesApiRequest> request, Pageable pageable){
        SalesApiRequest salesApiRequest = request.getData();
        Page<Sales> salesEntities = salesRepository.SearchList(salesApiRequest.getId(), salesApiRequest.getStatus2(), salesApiRequest.getStatus3(), salesApiRequest.getProductId(), salesApiRequest.getRegdate1(), salesApiRequest.getRegdate2(), pageable);
        List<SalesListApiResponse> salesListApiResponseList = salesEntities.stream()
                .map(sales -> {
                    Product product = sales.getProduct();

                    SalesListApiResponse salesListApiResponse = SalesListApiResponse.builder()
                            .productId(product.getId())
                            .salesId(sales.getId())
                            .regdate(sales.getRegdate())
                            .status2(sales.getStatus2())
                            .status3(sales.getStatus3())
                            .build();

                    return salesListApiResponse;
                }).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((salesEntities.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > salesEntities.getTotalPages()) {
            endPage = salesEntities.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(salesEntities.getTotalPages())
                .totalElements(salesEntities.getTotalElements())
                .currentPage(salesEntities.getNumber())
                .currentElements(salesEntities.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return Header.OK(salesListApiResponseList,pagination);
    }


    public Header delete(Long id){
        Optional<Sales> sales = salesRepository.findById(id);
        return sales.map(sale ->{
            baseRepository.delete(sale);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    public Header<SalesApiResponse> detail(Long id) {
        return salesRepository.findById(id)
                .map(sales -> response(sales))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    // 관리자 판매 입찰 상세
    public Header<SalesAdminApiResponse> salesinfo(Long id){
        Sales sales = salesRepository.getById(id);
        Customer customer = sales.getCustomer();
        Product product = sales.getProduct();
        Account account = sales.getAccount();

        SalesAdminApiResponse salesAdminApiResponse = SalesAdminApiResponse.builder()
                .userid(customer.getUserid())
                .email(customer.getEmail())
                .regdate(sales.getRegdate())
                .productName(product.getName())
                .productId(product.getId())
                .size(sales.getSizeType())
                .price(sales.getPrice())
                .status2(sales.getStatus2())
                .name(account.getName())
                .bank(account.getBank())
                .accountNumber(account.getAccountNumber())
                .build();

        return Header.OK(salesAdminApiResponse);
    }

    // 사용자 판매내역 상세
    public Header<SalesUserApiResponse> detailInfo(Long id){
        Sales sales = salesRepository.getById(id);

        Product product = sales.getProduct();

        Address address = sales.getAddress();

        Account account = sales.getAccount();

        Long salesPrice = salesRepository.findBySizeTypeAndProductIdAndStatus1(sales.getSizeType(), product.getId());

        Long purchasePrice = purchaseRepository.findBySizeTypeAndProductId(sales.getSizeType(), product.getId());

        SalesUserApiResponse salesUserApiResponse = SalesUserApiResponse.builder()
                .orderNumber(sales.getId())
                .period(sales.getPeriod())
                .status1(sales.getStatus1())
                .status2(sales.getStatus2())
                .productId(product.getId())
                .productName(product.getName())
                .size(sales.getSizeType())
                .originFileName(product.getProImgList().get(0).getOrigFileName())
                .price(sales.getPrice())
                .regdate(sales.getRegdate())
                .bank(account.getBank())
                .accountNumber(account.getAccountNumber())
                .name(address.getName())
                .hp(address.getHp())
                .zipcode(address.getZipcode())
                .address1(address.getDetail1())
                .address2(address.getDetail2())
                .salesPrice(salesPrice)
                .purchasePrice(purchasePrice)
                .build();
        return Header.OK(salesUserApiResponse);
    }

    public SalesApiResponse response(Sales sales){
        SalesApiResponse salesApiResponse = SalesApiResponse.builder()
                .id(sales.getId())
                .productId(sales.getProduct().getId())
                .customerId(sales.getCustomer().getId())
                .price(sales.getPrice())
                .period(sales.getPeriod())
                .originFileName(sales.getProduct().getProImgList().get(0).getFilePath())
                .sizeType(sales.getSizeType())
                .status1(sales.getStatus1())
                .status2(sales.getStatus2())
                .regdate(sales.getRegdate())
                .build();
        return salesApiResponse;
    }

}
