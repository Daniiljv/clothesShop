package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepo extends JpaRepository<Buyer, Long> {
}
