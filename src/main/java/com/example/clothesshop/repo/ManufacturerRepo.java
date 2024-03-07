package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByDeleteDateIsNullAndId(Long id);

    List<Manufacturer> findAllByDeleteDateIsNull();
}
