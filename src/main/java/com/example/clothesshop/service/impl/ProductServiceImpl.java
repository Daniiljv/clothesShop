package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.FindProductByBodyDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.mapper.ProductMapper;
import com.example.clothesshop.repo.ProductRepo;
import com.example.clothesshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;
    private final ProductMapper productMapper;

    @Override
    public CreateProductDto createProduct(CreateProductDto productDto) {

        try {
            repo.save(productMapper.toEntity(productDto));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = repo.findByDeleteDateIsNullAndId(id);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = repo.findAllByDeleteDateIsNull();
        return productMapper.toDtoList(products);
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
        return productMapper.toDtoList(products);
    }

    @Override
    public List<ProductDto> findProductsByColor(String color) {
        List<Product> products = repo.findProductsByColor(color);
        return productMapper.toDtoList(products);
    }



    public List<ProductDto> findByBody(FindProductByBodyDto productToFind){
        List<Product> products = repo.findByBody(productToFind.getPrices(),
                                                 productToFind.getColors(),
                                                 productToFind.getManufacturers());
        return productMapper.toDtoList(products);
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
