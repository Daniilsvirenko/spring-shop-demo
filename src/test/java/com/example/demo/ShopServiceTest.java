package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // 1. Подключаем Mockito к JUnit 5
class ShopServiceTest {

    @Mock // 2. Создаем "куклу" репозитория (фейковая база)
    private ProductRepository productRepository;

    @InjectMocks // 3. Вставляем эту куклу в настоящий сервис
    private ShopService shopService;

    @Test
    void getProductById_WhenProductExists_ShouldReturnProduct() {
        // --- A: ARRANGE (Подготовка) ---
        // Говорим кукле: "Если у тебя спросят товар с ID 1, верни вот этот объект"
        Product mockProduct = new Product();
        ReflectionTestUtils.setField(mockProduct, "id", 1L);
        mockProduct.setName("Test Product");
        mockProduct.setPrice(100);

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        // --- A: ACT (Действие) ---
        // Вызываем реальный метод сервиса
        Product result = shopService.getProductById(1L);

        // --- A: ASSERT (Проверка) ---
        // Проверяем, что сервис вернул то, что мы ожидали
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(100, result.getPrice());

        // Проверяем, что сервис действительно один раз сходил в репозиторий
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getProductById_WhenProductNotExists_ShouldReturnNull() {
        // --- ARRANGE ---
        // Говорим: "Товара 999 не существует" (верни пустой Optional)
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        // --- ACT ---
        Product result = shopService.getProductById(999L);

        // --- ASSERT ---
        assertNull(result);
    }
}