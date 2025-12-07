package com.demandforecast.repository;

import com.demandforecast.entity.Product;
import com.demandforecast.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

