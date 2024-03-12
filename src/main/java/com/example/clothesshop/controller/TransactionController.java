package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateTransactionDto;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.service.impl.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Transaction was created successfully ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateTransactionDto.class))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Failed to add transaction to database")
    })
    @Operation(summary = "This road creates transaction")
    @PostMapping("create")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionDto transactionDto){
        try {
            return new ResponseEntity<>(service.createTransaction(transactionDto), HttpStatus.OK);
        }catch (IllegalArgumentException i){
            return new ResponseEntity<>("There is no enough product in stock", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Transactions are found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))}),            @ApiResponse(responseCode = "404",
                    description = "Failed to find any transactions")
    })
    @Operation(summary = "This road returns all transactions by product id")
    @GetMapping("/product/{productId}")
    public List<Transaction> findAllByProductId(@PathVariable Long productId){
        return service.findAllByProductId(productId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Transactions are found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))}),            @ApiResponse(responseCode = "404",
            description = "Failed to find any transactions")
    })
    @Operation(summary = "This road returns all transactions by buyer id")
    @GetMapping("/buyer/{buyerId}")
    public List<Transaction> findAllBuyerTransactions(@PathVariable Long buyerId){
        return service.findAllBuyerTransactions(buyerId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Transactions are found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))}),            @ApiResponse(responseCode = "404",
            description = "Failed to find any transactions")
    })
    @Operation(summary = "This road returns all transactions by salesman id")
    @GetMapping("/salesman/{salesmanId}")
    public List<Transaction> findAllSalesmanTransactions(@PathVariable Long salesmanId) {
        return service.findAllBuyerTransactions(salesmanId);
    }

}
