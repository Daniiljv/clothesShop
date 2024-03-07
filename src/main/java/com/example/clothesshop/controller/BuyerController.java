package com.example.clothesshop.controller;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDto;
import com.example.clothesshop.service.impl.BuyerServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/buyers")
public class BuyerController {
    private final BuyerServiceImpl service;

    @PostMapping
    public ResponseEntity<CreateBuyerDto> create(CreateBuyerDto newBuyer){
        try {
            return new ResponseEntity<>(service.create(newBuyer), HttpStatus.CREATED);
        }catch (RuntimeException r){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuyerDto> findById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch (EntityNotFoundException enf){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<BuyerDto>> findAll(){
        List<BuyerDto> buyerDtoList = service.findAll();
        if(!buyerDtoList.isEmpty()){
            return new ResponseEntity<>(buyerDtoList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException r){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}
