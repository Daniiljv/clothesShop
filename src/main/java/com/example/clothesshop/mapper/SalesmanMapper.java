package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Salesman;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {
    Salesman toEntity(SalesmanDto salesmanDto);
    Salesman toEntity(CreateSalesmanDto salesmanDto);
    SalesmanDto toDto(Salesman salesman);

    List<Salesman> toEntityList(List<SalesmanDto> salesmanDtoList);
    List<SalesmanDto> toDtoList(List<Salesman> salesmanList);

}
