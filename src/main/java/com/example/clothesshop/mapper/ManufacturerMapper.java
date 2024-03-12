package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.entity.Manufacturer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {
    ManufacturerDto toDto(Manufacturer manufacturer);
    Manufacturer toEntity(ManufacturerDto manufacturerDto);
    Manufacturer toEntity(CreateManufacturerDto manufacturerDto);
    List<Manufacturer> toManufacturerEntityList(List<ManufacturerDto> manufacturerDtoList);
    List<ManufacturerDto> toManufacturerDtoList(List<Manufacturer> manufacturerList);
}
