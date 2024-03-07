package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.service.impl.SalesmanServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Salesman was created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateSalesmanDto.class))}),
            @ApiResponse(responseCode = "401",
                    description = "Failed to add salesman to database")
    })
    @Operation(summary = "This road creates salesman")
    @PostMapping("create")
    public ResponseEntity<CreateSalesmanDto> create(CreateSalesmanDto salesmanDto){
        CreateSalesmanDto salesmanToCreate = service.createSalesman(salesmanDto);
        if(salesmanToCreate != null){
            return new ResponseEntity<>(salesmanToCreate, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Salesman was found successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalesmanDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find salesman")
    })
    @Operation(summary = "This road finds salesman by id")
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Salesmen were found successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SalesmanDto.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find any salesmen")
    })
    @Operation(summary = "This road shows all salesmen in database")
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Salesman was deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Failed to delete salesman from database")
    })
    @Operation(summary = "This road deletes salesman")
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id){

        if(service.deleteById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
