package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDto;
import com.example.clothesshop.entity.Buyer;
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
    @Override
    public BuyerDto findById(Long id) {

        Buyer buyer = repo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Buyer with id " + id + " is not found"));
        return BuyerDto.builder()
                .id(buyer.getId())
                .name(buyer.getName())
                .surname(buyer.getSurname())
                .build();
    }

    @Override
    public List<BuyerDto> findAll() {
        List<Buyer> buyerList = repo.findAll();
        List<BuyerDto> buyerDtoList = new ArrayList<>();

        for(Buyer buyer : buyerList){
            BuyerDto buyerDto = BuyerDto.builder()
                    .id(buyer.getId())
                    .name(buyer.getName())
                    .surname(buyer.getSurname())
                    .build();
            buyerDtoList.add(buyerDto);
        }
        return buyerDtoList;
    }

    @Override
    public CreateBuyerDto create(CreateBuyerDto newBuyer) {
        Buyer buyer = Buyer.builder()
                .name(newBuyer.getName())
                .surname(newBuyer.getSurname())
                .build();
        try{
            repo.save(buyer);
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
