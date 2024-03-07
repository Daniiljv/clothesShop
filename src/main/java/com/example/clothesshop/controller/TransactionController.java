package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateTransactionDto;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.service.impl.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/transactions/")

public class TransactionController {

    private final TransactionServiceImpl service;

    @PostMapping("create")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionDto transactionDto){
        try {
            return new ResponseEntity<>(service.createTransaction(transactionDto), HttpStatus.OK);
        }catch (IllegalArgumentException i){
            return new ResponseEntity<>("There is no enough product in stock",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{productId}")
    public List<Transaction> findAllByProductId(@PathVariable Long productId){
        return service.findAllByProductId(productId);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<Transaction> findAllBuyerTransactions(@PathVariable Long buyerId){
        return service.findAllBuyerTransactions(buyerId);
    }

    @GetMapping("/salesman/{salesmanId}")
    public List<Transaction> findAllSalesmanTransactions(@PathVariable Long salesmanId) {
        return service.findAllBuyerTransactions(salesmanId);
    }

}
