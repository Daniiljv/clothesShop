package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesmanRepo extends JpaRepository<Salesman, Long> {
    Salesman findSalesmanByDeleteDateIsNullAndId(Long id);

    List<Salesman> findAllByDeleteDateIsNull();
}
