package com.project.kream.Service;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import com.project.kream.Model.request.CustomerApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.PurchaseRepository;
import com.project.kream.Repository.SalesRepository;
import com.project.kream.Repository.Specification.CustomerSpecification;
import com.project.kream.Repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CustomerService extends BaseService<CustomerApiRequest, CustomerApiResponse, Customer> {
    private final CustomerRepository customerRepository;
    private final SalesRepository salesRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerSpecification customerSpecification;
    private final StyleCustomerService styleCustomerService;
    private final WithdrawalRepository withdrawalRepository;
    private final PurchaseRepository purchaseRepository;
    private final WithdrawalService withdrawalService;

    public Header<Long> create(Header<CustomerApiRequest> request) {
        CustomerApiRequest customerApiRequest = request.getData();
        Customer customer1 = baseRepository.save(customerApiRequest.toEntity(passwordEncoder.encode(customerApiRequest.getUserpw())));

        String email = customer1.getEmail();

        styleCustomerService.create(customer1.getId(), email.substring(0,email.indexOf("@")));
        return Header.OK(customer1.getId());
    }

    public Header<CustomerApiResponse> read(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없음"));
        return Header.OK(new CustomerApiResponse(customer));
    }


    public int delete(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer newCustomer = optionalCustomer.get();
       withdrawalService.create(newCustomer.getEmail(), newCustomer.getHp());

        return optionalCustomer.map(customer -> {
            baseRepository.delete(customer);
            return 1;
        }).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
    }

    public Long session(String email){
        Customer customer = customerRepository.getByEmail(email);
        return customer.getId();
    }

    public Boolean pwCheck(Long id, String userpw){
        Customer customer = baseRepository.getById(id);
        return passwordEncoder.matches(userpw, customer.getUserpw());
    }

    @Transactional
    public Long update(Header<CustomerApiRequest> request) {
        CustomerApiRequest customerApiRequest = request.getData();
        Customer customer = customerRepository.findById(customerApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("해당유저 없음"));

        customer.update(customerApiRequest.getEmail(), customer.getUserid(), customer.getUserpw(), customerApiRequest.getHp(), customerApiRequest.getShoesize(), customerApiRequest.getAgreement(), customerApiRequest.getPrivacyPolicy(), customerApiRequest.getSmsAllow(), customerApiRequest.getEmailAllow(), customerApiRequest.getImage(), customerApiRequest.getPoint(), customerApiRequest.getMessage());
        return customerApiRequest.getId();
    }

    public Header<List<CustomerListApiResponse>> List(CustomerType type, Pageable pageable){
        Page<Customer> customerList = customerRepository.findAllByType(type, pageable);
        List<CustomerListApiResponse> customerListApiResponseList = customerList.stream()
                .map(CustomerListApiResponse::new)
                .collect(Collectors.toList());

        int countPage = 5;
        int startPage = (( customerList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage >  customerList.getTotalPages()) {
            endPage =  customerList.getTotalPages();
        }

        return Header.OK(customerListApiResponseList, new Pagination(customerList, startPage, endPage));
    }

    public void searchPW(String email, String hp){
        Customer customer = customerRepository.findByEmailAndHp(email, hp);
        mailService.execMail(customer.getEmail());
    }

    public StringBuffer searchEmail(String hp){
        Customer customer = customerRepository.findByHp(hp);
        StringBuffer sb = new StringBuffer();

        sb.append(customer.getEmail());

        int cnt = sb.substring(1, sb.indexOf("@")).length();

        sb.replace(1, sb.indexOf("@")-1, "*".repeat(cnt));
        return sb;
    }


    // 마이페이지
    public Header<CustomerMypage1ApiResponse> mypage(Long id){
        Customer customer = baseRepository.getById(id);

        List<Purchase> purchaseList = customer.getPurchaseList();
        List<CustomerMypagePurchaseApiResponse> customerMypagePurchaseApiResponseList = purchaseList.stream()
                .map(purchase -> {
                    Product product = purchase.getProduct();
                    return new CustomerMypagePurchaseApiResponse(product, purchase);
                }).collect(Collectors.toList());

        List<Sales> salesList = customer.getSalesList();
        List<CustomerMypageSalesApiResponse> customerMypageSalesApiResponseList = salesList.stream()
                .map(sales -> {
                    Product product = sales.getProduct();
                    return new CustomerMypageSalesApiResponse(product, sales);
                }).collect(Collectors.toList());

        List<Cart> cartList = customer.getCartList();
        List<CustomerMypageCartApiResponse> customerMypageCartApiResponseList = cartList.stream()
                .map(cart -> {
                    Product product = cart.getProduct();
                    return new CustomerMypageCartApiResponse(product, cart, salesRepository.findByProductId(product.getId()));
                }).collect(Collectors.toList());

        return Header.OK(new CustomerMypage1ApiResponse(customer, customerMypagePurchaseApiResponseList, customerMypageSalesApiResponseList, customerMypageCartApiResponseList));
    }

    // 구매내역
    public Header<List<CustomerPurchaseInfoApiResponse>> purchaseInfo(Long id, String regdate1, String regdate2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDateTime startDatetime = LocalDate.parse(regdate1, formatter).atStartOfDay();
        LocalDateTime endDatetime = LocalDate.parse(regdate2, formatter).atTime(23, 59, 59);

        List<Purchase> purchaseList = purchaseRepository.findAllByCustomerIdAndRegdate(id, startDatetime, endDatetime);

        List<CustomerPurchaseInfoApiResponse> customerPurchaseInfoApiResponseList = purchaseList.stream()
                .map(purchase -> {
                    Product product = purchase.getProduct();
                    return new CustomerPurchaseInfoApiResponse(product, purchase);
                }).collect(Collectors.toList());

        return Header.OK(customerPurchaseInfoApiResponseList);
    }

    // 판매내역
    public Header<List<CustomerSalesInfoApiResponse>> salesInfo(Long id, String regdate1, String regdate2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDateTime startDatetime = LocalDate.parse(regdate1, formatter).atStartOfDay();
        LocalDateTime endDatetime = LocalDate.parse(regdate2, formatter).atTime(23, 59, 59);

        List<Sales> salesList = salesRepository.findAllByCustomerIdAndRegdate(id, startDatetime, endDatetime);
        List<CustomerSalesInfoApiResponse> customerSalesInfoApiResponseList = salesList.stream()
                .map(sales -> {
                    Product product = sales.getProduct();
                    return new CustomerSalesInfoApiResponse(product, sales);
                }).collect(Collectors.toList());

        return Header.OK(customerSalesInfoApiResponseList);
    }


    // 찜목록
    public Header<List<CustomerCartInfoApiResponse>> cartinfo(Long id){
        Customer customer = baseRepository.getById(id);

        List<Cart> cartList = customer.getCartList();
        List<CustomerCartInfoApiResponse> customerCartInfoApiResponseList = cartList.stream()
                .map(cart -> {
                    Product product = cart.getProduct();
                    return new CustomerCartInfoApiResponse(product, cart, salesRepository.findByProductId(product.getId()));
                }).collect(Collectors.toList());

        return Header.OK(customerCartInfoApiResponseList);
    }


    // 관리자
    public Header<CustomerInfoApiResponse> customerInfo(Long id){
        Customer customer = baseRepository.getById(id);

        List<Address> addressList = customer.getAddressList();
        List<CustomerAddressApiResponse> customerAddressApiResponses = addressList.stream()
                .map(CustomerAddressApiResponse::new).collect(Collectors.toList());

        List<CardInfo> cardInfoList = customer.getCardInfoList();
        List<CustomerCardInfoApiResponse> customerCardInfoApiResponses = cardInfoList.stream()
                .map(CustomerCardInfoApiResponse::new).collect(Collectors.toList());

        Account account = customer.getAccountList().get(0);
        String AccountNumber = "";
        String Bank = "";
        String Name = "";
        if (account==null) {
            AccountNumber = "정보없음";
            Bank = "정보없음";
            Name = "정보없음";
        }else{
            AccountNumber = account.getAccountNumber();
            Bank = account.getBank();
            Name = account.getName();
        }

        return Header.OK(new CustomerInfoApiResponse(customer, Bank, AccountNumber, Name, customerAddressApiResponses, customerCardInfoApiResponses));
    }

    public Header<List<CustomerSearchApiResponse>> dataList(Header<CustomerApiRequest> request, Pageable pageable){
        Page<Customer> customerList = customerSpecification.searchCustomerList(request, pageable);

        List<CustomerSearchApiResponse> customerSearchApiResponseList = customerList.stream()
                .map(CustomerSearchApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((customerList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > customerList.getTotalPages()) {
            endPage = customerList.getTotalPages();
        }

        return Header.OK(customerSearchApiResponseList, new Pagination(customerList, startPage, endPage));
    }

}
