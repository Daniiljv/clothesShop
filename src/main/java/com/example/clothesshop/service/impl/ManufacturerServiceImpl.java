package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.entity.Manufacturer;
import com.example.clothesshop.mapper.ManufacturerMapper;
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
    private final ManufacturerMapper manufacturerMapper;

    @Override
    public CreateManufacturerDto createManufacturer(CreateManufacturerDto manufacturerDto) {

        try {
            repo.save(manufacturerMapper.toEntity(manufacturerDto));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();
        }
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto findById(Long id) {

            Manufacturer manufacturer = repo.findByDeleteDateIsNullAndId(id);

        return manufacturerMapper.toDto(manufacturer);
        }

        @Override
        public List<ManufacturerDto> getAllManufacturer () {

            List<Manufacturer> manufacturers = repo.findAllByDeleteDateIsNull();
            return manufacturerMapper.toManufacturerDtoList(manufacturers);
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
