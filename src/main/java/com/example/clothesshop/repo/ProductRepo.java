package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByDeleteDateIsNullAndId(Long id);

    List<Product> findAllByDeleteDateIsNull();

    List<Product> findAllByDeleteDateIsNullAndManufacturerId(Long id);

    @Query("SELECT p FROM Product p WHERE p.color = :color")
    List<Product> findProductsByColor(@Param("color") String color);

    @Query("select p from Product p " +
            "where p.price > :price " +
            "and p.color in :colors " +
            "and p.manufacturer.name in :manufacturers")
    List<Product> findByBody(@Param("price") double price,
                             @Param("colors") List<String> colors,
                             @Param("manufacturers") List<String> manufacturers);

    @Query("select p.inStock from Product p " +
            "where p.id = :id ")
    Long countOfProduct(@Param("id") Long id);

}
