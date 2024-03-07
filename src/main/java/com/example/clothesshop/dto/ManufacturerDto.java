package com.example.clothesshop.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManufacturerDto {

    private Long id;
    private String name;
    private String address;
    private String contactInfo;

}
