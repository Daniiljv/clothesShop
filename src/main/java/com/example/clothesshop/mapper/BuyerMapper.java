package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDto;
import com.example.clothesshop.entity.Buyer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuyerMapper {
    Buyer toEntity(BuyerDto buyerDto);
    Buyer toEntity(CreateBuyerDto buyerDto);
    BuyerDto toDto(Buyer buyer);

    List<BuyerDto> toBuyerDtoList(List<Buyer> buyers);
    List<Buyer> toBuyerEntityList(List<BuyerDto> buyerDtoList);
}
