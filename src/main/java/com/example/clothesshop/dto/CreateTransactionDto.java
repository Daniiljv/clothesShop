package com.example.clothesshop.dto;

import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.entity.Salesman;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionDto {
    private Long id;
    private Buyer buyer;
    private Product product;
    private Salesman salesman;
    private Long countOfProduct;

}
