package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Manufacturer;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.entity.Salesman;
import com.example.clothesshop.mapper.ManufacturerMapper;
import com.example.clothesshop.mapper.SalesmanMapper;
import com.example.clothesshop.repo.SalesmanRepo;
import com.example.clothesshop.service.SalesmanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepo repo;
    private final SalesmanMapper salesmanMapper;
    private final ManufacturerMapper manufacturerMapper;

    @Override
    public CreateSalesmanDto createSalesman(CreateSalesmanDto salesmanToCreate) {
        try {
            repo.save(salesmanMapper.toEntity(salesmanToCreate));
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return salesmanToCreate;
    }

    @Override
    public SalesmanDto getById(long id) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(id);
        return salesmanMapper.toDto(salesman);
    }

    @Override
    public List<SalesmanDto> getAll() {
        List<Salesman> salesmen = repo.findAllByDeleteDateIsNull();
        return salesmanMapper.toDtoList(salesmen);
    }

    @Override
    public Long getCountOfSalesmanProducts(Long salesmanId) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(salesmanId);
        List<Product> productList = salesman.getProducts();
        return productList.stream().count();
    }

    @Override
    public Double avgPriceOfSalesmanProducts(Long salesmanId) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(salesmanId);
        List<Product> productList = salesman.getProducts();
        if (productList.isEmpty()) {
            throw new NullPointerException();
        } else {
            double resultOfSum = productList.stream().mapToDouble(Product::getPrice).sum();
            return resultOfSum / productList.size();
        }
    }

    @Override
    public Double getSalesmanBudget(Long salesmanId) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(salesmanId);
        List<Product> productList = salesman.getProducts();
        if (productList.isEmpty()) {
            throw new NullPointerException();
        } else {
            return productList.stream().mapToDouble(Product :: getPrice).sum();
        }
    }

    @Override
    public List<Integer> getSalesmanPricesWithDiscount(Long salesmanId) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(salesmanId);
        List<Product> productList = salesman.getProducts();
        if (productList.isEmpty()) {
            throw new NullPointerException();
        } else {
            return productList.stream().map(p-> (int)(p.getPrice()*0.5)).toList();
        }
    }

    public List<String> newSalesmanProductsNames(Long salesmanId){
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(salesmanId);
        List<Product> productList = salesman.getProducts();
        if (productList.isEmpty()) {
            throw new NullPointerException();
        } else {
            List<String> productsNames = productList.stream().map(Product :: getName).toList();
            return  productsNames.stream().map(s-> s.concat("s")).toList();
        }
    }



    @Override
    public boolean deleteById(Long id) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(id);

        if (salesman != null) {
            salesman.setDeleteDate(new Date(System.currentTimeMillis()));
            repo.save(salesman);
            return true;
        }
        return false;
    }

    @Override
    public List<ManufacturerDto> getSalesmanManufacturers(Long salesmanId) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(salesmanId);
        List<Product> productList = salesman.getProducts();

        List<Manufacturer> manufacturerList = productList.stream().map(Product :: getManufacturer).toList();
        return manufacturerMapper.toManufacturerDtoList(manufacturerList);
    }
}
