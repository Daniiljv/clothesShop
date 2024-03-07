package com.example.clothesshop.service;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;

import java.util.List;

public interface SalesmanService {

    CreateSalesmanDto createSalesman(CreateSalesmanDto salesmanDto);

    SalesmanDto getById(long id);

    List<SalesmanDto> getAll();

    boolean deleteById(Long id);

}
