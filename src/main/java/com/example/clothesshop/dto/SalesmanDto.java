package com.example.clothesshop.dto;

import com.example.clothesshop.entity.Product;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesmanDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Product> products;

}
