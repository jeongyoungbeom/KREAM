package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.response.TransactionListApiResponse;
import com.project.kream.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionRestController {
    private final TransactionService transactionService;

    @GetMapping("/api/trans_list/{productId}")
    public Header<List<TransactionListApiResponse>> transList(@PathVariable Long productId){
        return transactionService.transList(productId);
    }

}
