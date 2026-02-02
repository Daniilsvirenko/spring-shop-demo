package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // 1. Эта аннотация запускает Spring и H2 базу данных
class ProductRepositoryTest {

    @Autowired // 2. Тут мы просим НАСТОЯЩИЙ репозиторий (не Mock!)
    private ProductRepository productRepository;

    @Test
    void saveAndFindProduct_ShouldPersistData() {
        // --- ARRANGE ---
        Product product = new Product();
        product.setName("Integration Test Phone");
        product.setPrice(999);

        // --- ACT ---
        // Сохраняем в реальную (in-memory) базу
        Product savedProduct = productRepository.save(product);

        // --- ASSERT ---
        assertNotNull(savedProduct.getId()); // Проверяем, что база присвоила ID!

        // Попробуем найти его через findById
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals("Integration Test Phone", foundProduct.get().getName());
    }
}