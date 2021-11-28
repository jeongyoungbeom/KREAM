package com.project.kream.Service;

import com.project.kream.Model.Header;
import com.project.kream.Model.enumclass.CustomerType;
import com.project.kream.Model.enumclass.DeliveryStatus;
import com.project.kream.Model.response.DashBoardApiResponse;
import com.project.kream.Model.response.DeliveryCntApiResponse;
import com.project.kream.Model.response.MainApiResponse;
import com.project.kream.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final StyleRepository styleRepository;
    private final TransactionRepository transactionRepository;
    private final WithdrawalRepository withdrawalRepository;
    private final DeliveryRepository deliveryRepository;

    public Header<DashBoardApiResponse> index(){
        LocalDate nowTime = LocalDate.now();
        LocalDateTime startTime = nowTime.atStartOfDay();
        LocalDateTime endTime = LocalDateTime.of(nowTime, LocalTime.of(23, 59, 59));

        Long CustomerCnt = customerRepository.CustomerCnt(CustomerType.일반);
        Long ProductCnt = productRepository.ProductCnt();
        Long StyleCnt = styleRepository.StyleCnt();
        Long TransactionCnt = transactionRepository.TransactionCnt();
        Long NowCustomerCnt = customerRepository.NowCustomerCnt(startTime, endTime);
        Long NowWithdrawalCnt = withdrawalRepository.NowWithdrawalCnt(startTime, endTime);
        Long NowTransactionCnt = transactionRepository.NowTransactionCnt(startTime, endTime);


        Long delivery = deliveryRepository.deliveryCnt(DeliveryStatus.배송중);
        Long waiting = deliveryRepository.deliveryCnt(DeliveryStatus.배송대기);
        Long complete = deliveryRepository.deliveryCnt(DeliveryStatus.배송완료);

        Long sum = delivery+waiting+complete;
        // 대기
        String deliveryCnt = String.format("%.2f" ,((double)delivery / (double)sum ) * 100);
        // 완료
        String completedCnt = String.format("%.2f" ,((double)complete / (double)sum) * 100);
        // 중
        String waitingCnt = String.format("%.2f" ,((double)waiting / (double)sum) * 100);

        DashBoardApiResponse dashBoardApiResponse = DashBoardApiResponse.builder()
                .CustomerCnt(CustomerCnt)
                .ProductCnt(ProductCnt)
                .StyleCnt(StyleCnt)
                .TransactionCnt(TransactionCnt)
                .NowCustomerCnt(NowCustomerCnt)
                .NowWithdrawalCnt(NowWithdrawalCnt)
                .NowTransactionCnt(NowTransactionCnt)
                .deliveryCnt(deliveryCnt)
                .completedCnt(completedCnt)
                .waitingCnt(waitingCnt)
                .build();
        return Header.OK(dashBoardApiResponse);
    }
}
