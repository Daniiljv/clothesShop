package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.service.impl.SalesmanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/salesmen/")
public class SalesmanController {

    private final SalesmanServiceImpl service;

    @PostMapping("create")
    public ResponseEntity<CreateSalesmanDto> create(CreateSalesmanDto salesmanDto){
        CreateSalesmanDto salesmanToCreate = service.createSalesman(salesmanDto);
        if(salesmanToCreate != null){
            return new ResponseEntity<>(salesmanToCreate, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("getById/{id}")
    public ResponseEntity<SalesmanDto> getById(@PathVariable Long id){
        SalesmanDto salesman = service.getById(id);
        if(salesman != null){
            return new ResponseEntity<>(salesman, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<SalesmanDto>> getAll(){
        List<SalesmanDto> salesmanDtoList = service.getAll();
        if(!salesmanDtoList.isEmpty()){
            return new ResponseEntity<>(salesmanDtoList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id){

        if(service.deleteById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
