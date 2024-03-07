package com.example.clothesshop.dto;

import com.example.clothesshop.entity.Manufacturer;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Manufacturer manufacturer;
    private Double price;
    private String size;
    private String color;
    private Long inStock;

}
