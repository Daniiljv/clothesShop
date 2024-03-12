package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Product;
import com.example.clothesshop.entity.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesmanRepo extends JpaRepository<Salesman, Long> {
    Salesman findSalesmanByDeleteDateIsNullAndId(Long id);

    List<Salesman> findAllByDeleteDateIsNull();

    @Query("select p from Salesman s join s.products p where s.id = :salesmanId")
    List<Product> findAllSalesmanProducts(@Param("salesmanId") Long salesmanId);
}
