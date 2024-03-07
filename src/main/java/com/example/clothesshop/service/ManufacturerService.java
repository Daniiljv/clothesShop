package com.example.clothesshop.service;

import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;

import java.util.List;

public interface ManufacturerService {

    CreateManufacturerDto createManufacturer(CreateManufacturerDto manufacturer);
    ManufacturerDto findById(Long id);
    List<ManufacturerDto> getAllManufacturer();
    boolean deleteById(Long id);
}
