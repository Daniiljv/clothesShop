package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.service.impl.ManufacturerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/manufacturers")

public class ManufacturerController {

    private final ManufacturerServiceImpl service;

    @PostMapping
    public ResponseEntity<CreateManufacturerDto> create(@RequestBody CreateManufacturerDto manufacturerDto){

        CreateManufacturerDto createdManufacturer = service.createManufacturer(manufacturerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdManufacturer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDto> getById(@PathVariable Long id){
        ManufacturerDto manufacturerDto = service.findById(id);
        if(manufacturerDto != null){
            return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerDto>> getAll(){
        List<ManufacturerDto> manufacturerDtoList = service.getAllManufacturer();
        if(!manufacturerDtoList.isEmpty()){
            return new ResponseEntity<>(manufacturerDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
       if(service.deleteById(id)){
           return new ResponseEntity<>(true, HttpStatus.OK);
       }
       else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
