package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.service.impl.ManufacturerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Manufacturer was created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateManufacturerDto.class))}),
            @ApiResponse(responseCode = "401",
                    description = "Failed to add manufacturer to database")
    })
    @Operation(summary = "This road creates manufacturer")
    @PostMapping
    public ResponseEntity<CreateManufacturerDto> create(@RequestBody CreateManufacturerDto manufacturerDto) {
        try {
            CreateManufacturerDto createdManufacturer = service.createManufacturer(manufacturerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdManufacturer);
        } catch (RuntimeException r){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Manufacturer was found successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ManufacturerDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find manufacturer")
    })
    @Operation(summary = "This road finds manufacturer by id")
    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDto> getById(@PathVariable Long id) {
        ManufacturerDto manufacturerDto = service.findById(id);
        if (manufacturerDto != null) {
            return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Manufacturers were found successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ManufacturerDto.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find any manufacturers")
    })
    @Operation(summary = "This road shows all manufacturers in database")
    @GetMapping
    public ResponseEntity<List<ManufacturerDto>> getAll() {
        List<ManufacturerDto> manufacturerDtoList = service.getAllManufacturer();
        if (!manufacturerDtoList.isEmpty()) {
            return new ResponseEntity<>(manufacturerDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Manufacturer was deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Failed to delete manufacturer from database")
    })
    @Operation(summary = "This road deletes manufacturer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
