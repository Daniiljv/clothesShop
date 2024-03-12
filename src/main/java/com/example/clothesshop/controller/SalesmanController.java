package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.ManufacturerDto;
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
@RequestMapping("api/salesmen")
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
    public ResponseEntity<CreateSalesmanDto> create(CreateSalesmanDto salesmanDto) {
        CreateSalesmanDto salesmanToCreate = service.createSalesman(salesmanDto);
        if (salesmanToCreate != null) {
            return new ResponseEntity<>(salesmanToCreate, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
    public ResponseEntity<SalesmanDto> getById(@PathVariable Long id) {
        SalesmanDto salesman = service.getById(id);
        if (salesman != null) {
            return new ResponseEntity<>(salesman, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productCountBySalesman/{salesmanId}")
    public ResponseEntity<Long> getCountOfSalesmanProducts(@PathVariable Long salesmanId) {
        Long count = service.getCountOfSalesmanProducts(salesmanId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/getAvgPriceOfSalesmanProducts/{salesmanId}")
    public ResponseEntity<Double> avgPriceOfSalesmanProducts(@PathVariable Long salesmanId) {
        Double result = service.avgPriceOfSalesmanProducts(salesmanId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getSalesmanBudget/{salesmanId}")
    public ResponseEntity<Double> getSalesmanBudget(@PathVariable Long salesmanId) {
        Double result = service.getSalesmanBudget(salesmanId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getSalesmanPricesWithDiscount/{salesmanId}")
    public ResponseEntity<List<Integer>> getSalesmanPricesWithDiscount(@PathVariable Long salesmanId) {
        List<Integer> result = service.getSalesmanPricesWithDiscount(salesmanId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
@GetMapping("/newSalesmanProductsNames/{salesmanId}")
    public ResponseEntity<List<String>> newSalesmanProductsNames(@PathVariable Long salesmanId){
        List<String> result = service.newSalesmanProductsNames(salesmanId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getSalesmanManufacturers/{salesmanId}")
    public ResponseEntity<List<ManufacturerDto>> getSalesmanManufacturers(@PathVariable Long salesmanId){
        List<ManufacturerDto> result = service.getSalesmanManufacturers(salesmanId);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
    public ResponseEntity<List<SalesmanDto>> getAll() {
        List<SalesmanDto> salesmanDtoList = service.getAll();
        if (!salesmanDtoList.isEmpty()) {
            return new ResponseEntity<>(salesmanDtoList, HttpStatus.OK);
        } else {
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
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {

        if (service.deleteById(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
