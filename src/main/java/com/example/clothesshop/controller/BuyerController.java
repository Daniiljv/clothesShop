package com.example.clothesshop.controller;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDto;
import com.example.clothesshop.service.impl.BuyerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Buyer was created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateBuyerDto.class))}),
            @ApiResponse(responseCode = "401",
                    description = "Failed to add buyer to database")
    })
    @Operation(summary = "This road creates buyer")
    @PostMapping
    public ResponseEntity<CreateBuyerDto> create(CreateBuyerDto newBuyer){
        try {
            return new ResponseEntity<>(service.create(newBuyer), HttpStatus.CREATED);
        }catch (RuntimeException r){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buyer found successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BuyerDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find buyer")
    })
    @Operation(summary = "This road finds buyer by id")
    @GetMapping("/{id}")
    public ResponseEntity<BuyerDto> findById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch (EntityNotFoundException enf){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buyers were found successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BuyerDto.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find any buyers")
    })
    @Operation(summary = "This road shows all users in database")
    @GetMapping
    public ResponseEntity<List<BuyerDto>> findAll(){
        List<BuyerDto> buyerDtoList = service.findAll();
        if(!buyerDtoList.isEmpty()){
            return new ResponseEntity<>(buyerDtoList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buyer was deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Failed to delete buyer from database")
    })
    @Operation(summary = "This road deletes buyer")
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
