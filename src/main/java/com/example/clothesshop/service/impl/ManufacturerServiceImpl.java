package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.entity.Manufacturer;
import com.example.clothesshop.repo.ManufacturerRepo;
import com.example.clothesshop.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepo repo;

    @Override
    public CreateManufacturerDto createManufacturer(CreateManufacturerDto manufacturerDto) {

        Manufacturer manufacturer = Manufacturer.builder()
                .name(manufacturerDto.getName())
                .contactInfo(manufacturerDto.getContactInfo())
                .address(manufacturerDto.getAddress())
                .build();
        try {
            repo.save(manufacturer);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();
        }
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto findById(Long id) {

            Manufacturer manufacturer = repo.findByDeleteDateIsNullAndId(id);

        return ManufacturerDto.builder()
                    .id(manufacturer.getId())
                    .name(manufacturer.getName())
                    .address(manufacturer.getAddress())
                    .contactInfo(manufacturer.getContactInfo())
                    .build();
        }

        @Override
        public List<ManufacturerDto> getAllManufacturer () {

            List<Manufacturer> manufacturers = repo.findAllByDeleteDateIsNull();

            List<ManufacturerDto> manufacturerDtoList = new ArrayList<>();

            for (Manufacturer manufacturer : manufacturers) {

                ManufacturerDto manufacturerDto = ManufacturerDto.builder()
                        .id(manufacturer.getId())
                        .name(manufacturer.getName())
                        .address(manufacturer.getAddress())
                        .contactInfo(manufacturer.getContactInfo())
                        .build();
                manufacturerDtoList.add(manufacturerDto);
            }

            return manufacturerDtoList;
        }

        @Override
        public boolean deleteById (Long id){
            Manufacturer manufacturer = repo.findByDeleteDateIsNullAndId(id);

            if(manufacturer != null){
                manufacturer.setDeleteDate(new Date(System.currentTimeMillis()));
                repo.save(manufacturer);
                return true;
            }
            else{
                return false;
            }
        }
    }
