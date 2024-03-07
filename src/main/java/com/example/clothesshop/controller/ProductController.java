package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.FindProductByBodyDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.service.impl.ProductServiceImpl;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("api/products")

public class ProductController {

    private final ProductServiceImpl service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product created successfully ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateProductDto.class))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Failed to add product to database")
    })
    @Operation(summary = "This road creates product")
    @PostMapping
    public ResponseEntity<CreateProductDto> create(@RequestBody CreateProductDto productDto) {
        CreateProductDto createdProduct = service.createProduct(productDto);
        if (createdProduct != null) {
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find product")
    })

    @Operation(summary = "This road returns product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        ProductDto productDto = service.findById(id);
        if (productDto != null) {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Products are found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))}),

            @ApiResponse(responseCode = "404",
                    description = "Failed to find products")
    })

    @Operation(summary = "This road returns all products")
    @GetMapping("getAll")
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtoList = service.getAllProducts();
        if (!productDtoList.isEmpty()) {
            return new ResponseEntity<>(productDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Products are found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find products")
    })

    @Operation(summary = "This road returns product manufacturer id")
    @GetMapping("findAllByManufacturerId/{id}")
    public ResponseEntity<List<ProductDto>> findAllByManufacturerId(@PathVariable Long id) {
        List<ProductDto> products = service.findAllByManufacturerId(id);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Products are found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find products")
    })

    @Operation(summary = "This road returns product with specific color")
    @GetMapping("findProductsByColor")
    public ResponseEntity<List<ProductDto>> findProductsByColor(@RequestParam String color) {
        List<ProductDto> products = service.findProductsByColor(color);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Products are found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find products")
    })

    @Operation(summary = "This road returns product by specific body type")
    @PostMapping("findByBody")
    public ResponseEntity<List<ProductDto>> findByBody(@RequestBody FindProductByBodyDto productToFind) {
        List<ProductDto> products = service.findByBody(productToFind);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product is deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Failed to find product")
    })
    @Operation(summary = "This road deletes product by id")
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        if (service.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    git commit -m "first commit"
    git branch -M main
    git remote add origin https://github.com/Daniiljv/clothesShop.git
    git push -u origin main
}
