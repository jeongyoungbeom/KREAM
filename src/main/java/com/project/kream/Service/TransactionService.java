package com.project.kream.Service;

import com.project.kream.Model.Entity.Transaction;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.TransactionApiRequest;
import com.project.kream.Model.response.TransactionApiResponse;
import com.project.kream.Model.response.TransactionListApiResponse;
import com.project.kream.Repository.ProductRepository;
import com.project.kream.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService extends BaseService<TransactionApiRequest, TransactionApiResponse, Transaction> {
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    public Header<TransactionApiResponse> create(TransactionApiRequest request){
        Transaction transaction = Transaction.builder()
                .sizeType(request.getSizeType())
                .product(productRepository.getById(request.getProductId()))
                .build();

        Transaction newTransaction = baseRepository.save(transaction);

        return Header.OK(response(newTransaction));
    }

    public Header<List<TransactionListApiResponse>> transList(Long productId){
        List<Transaction> transactionList = transactionRepository.findAllByProductIdOrderByRegdateAsc(productId);
        List<TransactionListApiResponse> transactionListApiResponseList = transactionList.stream()
                .map(transaction -> {
                    TransactionListApiResponse transactionListApiResponse = TransactionListApiResponse.builder()
                            .sizeType(transaction.getSizeType())
                            .price(transaction.getPrice())
                            .regdate(transaction.getRegdate())
                            .build();
                    return transactionListApiResponse;
                }).collect(Collectors.toList());

        return Header.OK(transactionListApiResponseList);
    }

    public TransactionApiResponse response(Transaction transaction){
        TransactionApiResponse transactionApiResponse = TransactionApiResponse.builder()
                .id(transaction.getId())
                .sizeType(transaction.getSizeType())
                .productId(transaction.getProduct().getId())
                .build();
        return transactionApiResponse;
    }
}
