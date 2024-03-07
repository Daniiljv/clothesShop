package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateTransactionDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Salesman;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.repo.TransactionRepo;
import com.example.clothesshop.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final ProductServiceImpl productService;
    private final BuyerServiceImpl buyerService;
    private final SalesmanServiceImpl salesmanService;
    private final TransactionRepo repo;

    @Override
    public String createTransaction(CreateTransactionDto transactionToCreate) {
        ProductDto product = productService.findById(transactionToCreate.getProduct().getId());
        BuyerDto buyer = buyerService.findById(transactionToCreate.getBuyer().getId());
        SalesmanDto salesman = salesmanService.getById(transactionToCreate.getSalesman().getId());

        Buyer buyer1 = Buyer.builder()
                .id(buyer.getId())
                .name(buyer.getName())
                .surname(buyer.getSurname())
                .build();

        Product product1 = Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .manufacturer(product.getManufacturer())
                .price(product.getPrice())
                .size(product.getSize())
                .color(product.getColor())
                .inStock(product.getInStock())
                .build();

        Salesman salesman1 = Salesman.builder()
                .id(salesman.getId())
                .name(salesman.getName())
                .email(salesman.getEmail())
                .phoneNumber(salesman.getPhoneNumber())
                .products(salesman.getProducts())
                .build();

        if (product.getId() != null && buyer.getId() != null && salesman.getId() != null) {
            if (productService.getCountOfProduct(product.getId())
                    >= transactionToCreate.getCountOfProduct()) {

                Transaction transaction = Transaction.builder()
                        .buyer(buyer1)
                        .product(product1)
                        .countOfProduct(transactionToCreate.getCountOfProduct())
                        .salesman(salesman1)
                        .build();
                repo.save(transaction);

                productService.decreaseCountOfProduct(product.getId(), transaction.getCountOfProduct());
            } else {
                throw new IllegalArgumentException("Insufficient stock quantity");
            }
        } else throw new EntityNotFoundException();


        return "Successfully";
    }

    @Override
    public List<Transaction> findAll() {
        return repo.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Buy with id " + id + " is not found"));
    }

    @Override
    public List<Transaction> findAllByProductId(Long productId) {
        List<Transaction> transactionList = repo.findAllByProductId(productId);
        if (transactionList.isEmpty()) {
            throw new NullPointerException();
        } else return transactionList;
    }

    @Override
    public List<Transaction> findAllBuyerTransactions(Long buyerId) {
        List<Transaction> transactionList = repo.findAllBuyerTransactions(buyerId);
        if (transactionList.isEmpty()) {
            throw new NullPointerException();
        } else return transactionList;
    }

    @Override
    public List<Transaction> findAllSalesmanTransactions(Long salesmanId) {
        List<Transaction> transactionList = repo.findAllSalesmanTransactions(salesmanId);
        if (transactionList.isEmpty()) {
            throw new NullPointerException();
        } else return transactionList;
    }
}
