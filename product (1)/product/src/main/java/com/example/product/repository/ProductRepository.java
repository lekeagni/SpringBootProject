package com.example.product.repository;

import com.example.product.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Optional<ProductModel> findByName(String name);
}
