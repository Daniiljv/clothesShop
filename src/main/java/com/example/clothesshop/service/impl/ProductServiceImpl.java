package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.FindProductByBodyDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.repo.ProductRepo;
import com.example.clothesshop.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;

    @Override
    public CreateProductDto createProduct(CreateProductDto productDto) {

        Product productToSave = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .manufacturer(productDto.getManufacturer())
                .price(productDto.getPrice())
                .size(productDto.getSize())
                .color(productDto.getColor())
                .build();

        try {
            repo.save(productToSave);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = repo.findByDeleteDateIsNullAndId(id);

        return ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .inStock(product.getInStock())
                    .build();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = repo.findAllByDeleteDateIsNull();

        List<ProductDto> productsDto = new ArrayList<>();

        for (Product product : products) {

            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();

            productsDto.add(productDto);
        }
        return productsDto;
    }

    @Override
    public boolean deleteById(Long id) {
        Product product = repo.findByDeleteDateIsNullAndId(id);
        if(product != null){
            product.setDeleteDate(new Date(System.currentTimeMillis()));
            repo.save(product);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<ProductDto> findAllByManufacturerId(Long id) {
        List<Product> products = repo.findAllByDeleteDateIsNullAndManufacturerId(id);

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : products){
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductsByColor(String color) {
        List<Product> products = repo.findProductsByColor(color);

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : products){
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public List<ProductDto> findByBody(FindProductByBodyDto productToFind){
        List<Product> products = repo.findByBody(productToFind.getPrices(),
                                                 productToFind.getColors(),
                                                 productToFind.getManufacturers());

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : products){
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public Long getCountOfProduct(Long id) {
        return repo.countOfProduct(id);
    }

    @Override
    public Long decreaseCountOfProduct(Long id, Long count) {
     Product product = repo.findByDeleteDateIsNullAndId(id);
     Long oldCount = product.getInStock();
     Long newCount = oldCount - count;

     product.setInStock(newCount);
     repo.save(product);

     return newCount;
    }


}
