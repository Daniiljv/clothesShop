package com.example.clothesshop.service;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDto;
import com.example.clothesshop.entity.Buyer;


import java.util.List;

public interface BuyerService {
    BuyerDto findById(Long id);
    List<BuyerDto> findAll();

    CreateBuyerDto create(CreateBuyerDto newBuyer);

    void delete(Long id);
}
