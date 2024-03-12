package com.example.clothesshop.service;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.dto.SalesmanDto;

import java.util.List;

public interface SalesmanService {

    CreateSalesmanDto createSalesman(CreateSalesmanDto salesmanDto);

    SalesmanDto getById(long id);

    List<SalesmanDto> getAll();

    Long getCountOfSalesmanProducts(Long salesmanId);

    Double avgPriceOfSalesmanProducts(Long salesmanId);

    Double getSalesmanBudget(Long salesmanId);

    List<Integer> getSalesmanPricesWithDiscount(Long salesmanId);
    boolean deleteById(Long id);

    List<ManufacturerDto> getSalesmanManufacturers(Long salesmanId);

}
