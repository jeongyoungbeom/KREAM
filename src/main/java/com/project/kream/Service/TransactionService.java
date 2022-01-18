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

    public Long create(Header<TransactionApiRequest> request){

        TransactionApiRequest transactionApiRequest = request.getData();
        Transaction transaction = transactionRepository.save(transactionApiRequest.toEntity(productRepository.getById(transactionApiRequest.getProductId())));
        return transaction.getId();
    }

    public Header<List<TransactionListApiResponse>> transList(Long productId){
        List<Transaction> transactionList = transactionRepository.findAllByProductIdOrderByRegdateAsc(productId);
        List<TransactionListApiResponse> transactionListApiResponseList = transactionList.stream()
                .map(TransactionListApiResponse::new).collect(Collectors.toList());

        return Header.OK(transactionListApiResponseList);
    }

}
