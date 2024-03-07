package com.example.clothesshop.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FindProductByBodyDto {
    private Double prices;
    private List<String> colors;
    private List<String> manufacturers;

}
