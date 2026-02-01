package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Мы говорим: "Это хранилище для Product, у которого ID типа Long"
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByPriceLessThan(int maxPrice);
    List<Product> findByPriceGreaterThan(int minPrice);

    // Внутри пусто! Но здесь уже есть методы save(), findAll(), delete() и другие.
    // Это магия Spring Data.
}