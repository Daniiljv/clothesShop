package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t " +
            "where t.salesman.id = :salesmanId ")
    List<Transaction> findAllSalesmanTransactions(@Param("salesmanId") Long salesmanId);

    @Query(value = "select * from Transaction where buyer_id = ?", nativeQuery = true)
    List<Transaction> findAllBuyerTransactions(Long buyerId);

    List<Transaction> findAllByProductId(Long productId);


}
