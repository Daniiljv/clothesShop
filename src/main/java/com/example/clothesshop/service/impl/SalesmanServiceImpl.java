package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Salesman;
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
    @Override
    public CreateSalesmanDto createSalesman(CreateSalesmanDto salesmanToCreate) {

        Salesman salesman = Salesman.builder()
                .name(salesmanToCreate.getName())
                .email(salesmanToCreate.getEmail())
                .phoneNumber(salesmanToCreate.getPhoneNumber())
                .products(salesmanToCreate.getProducts())
                .build();

        try{
            repo.save(salesman);
        }catch (Exception e){
            log.error(e.getStackTrace().toString());
        }
        return salesmanToCreate;
    }

    @Override
    public SalesmanDto getById(long id) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(id);

            SalesmanDto salesmanDto = SalesmanDto.builder()
                    .id(salesman.getId())
                    .name(salesman.getName())
                    .email(salesman.getEmail())
                    .phoneNumber(salesman.getPhoneNumber())
                    .products(salesman.getProducts())
                    .build();

            return salesmanDto;
        }

    @Override
    public List<SalesmanDto> getAll() {
        List<Salesman> salesmen = repo.findAllByDeleteDateIsNull();

        List<SalesmanDto> salesmenDto = new ArrayList<>();

        for(Salesman salesman : salesmen){

            SalesmanDto salesmanDto = SalesmanDto.builder()
                    .id(salesman.getId())
                    .name(salesman.getName())
                    .email(salesman.getEmail())
                    .phoneNumber(salesman.getPhoneNumber())
                    .products(salesman.getProducts())
                    .build();
            salesmenDto.add(salesmanDto);
        }
        return salesmenDto;
    }

    @Override
    public boolean deleteById(Long id) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(id);

        if(salesman != null) {
            salesman.setDeleteDate(new Date(System.currentTimeMillis()));
            repo.save(salesman);
            return true;
        }
        return false;
    }

}
