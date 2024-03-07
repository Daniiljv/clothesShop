package com.example.clothesshop.dto;

import com.example.clothesshop.entity.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateProductDto {

    private String name;
    private String description;
    private Manufacturer manufacturer;
    private Double price;
    private String size;
    private String color;
    private Long inStock;

}
