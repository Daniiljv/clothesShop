package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDto;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.mapper.BuyerMapper;
import com.example.clothesshop.repo.BuyerRepo;
import com.example.clothesshop.service.BuyerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    private final BuyerRepo repo;
    private final BuyerMapper buyerMapper;
    @Override
    public BuyerDto findById(Long id) {

        Buyer buyer = repo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Buyer with id " + id + " is not found"));
        return buyerMapper.toDto(buyer);
    }

    @Override
    public List<BuyerDto> findAll() {
        List<Buyer> buyerList = repo.findAll();
return buyerMapper.toBuyerDtoList(buyerList);
    }

    @Override
    public CreateBuyerDto create(CreateBuyerDto newBuyer) {
        try{
            repo.save(buyerMapper.toEntity(newBuyer));
        }catch (Exception e){
            log.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();
        }
        return newBuyer;
    }

    @Override
    public void delete(Long id) {
        try {
            repo.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
