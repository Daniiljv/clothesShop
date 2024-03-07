package com.example.clothesshop.service;

import com.example.clothesshop.dto.CreateTransactionDto;
import com.example.clothesshop.entity.Transaction;


import java.util.List;

public interface TransactionService {
    String createTransaction(CreateTransactionDto createTransaction);
    List<Transaction> findAll();
    Transaction findById(Long id);

    List<Transaction> findAllByProductId(Long productId);

    List<Transaction> findAllBuyerTransactions(Long buyerId);
    List<Transaction> findAllSalesmanTransactions(Long salesmanId);
}
