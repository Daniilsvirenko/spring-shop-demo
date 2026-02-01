package com.example.demo; // У вас может быть другой пакет

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 1. ЯРЛЫК: Говорим Спрингу, что этот класс отвечает на веб-запросы
public class HelloController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/catalog")
    public List<Product> catalog() {
       return shopService.getProductList();
    }
    @PostMapping("/catalog")
    public String addProduct(@RequestBody Product product) {
        shopService.saveProduct(product);
        return "Added product " + product.getName() + " successfully";
    }

    // Чтение одного конкретного товара
    // {id} в адресе превратится в переменную Long id
    @GetMapping("/catalog/{id}")
    public Product getProduct(@PathVariable Long id) {
        return shopService.getProductById(id);
    }

    // Удаление товара
    @DeleteMapping("/catalog/{id}")
    public String deleteProduct(@PathVariable Long id) {
        shopService.deleteProduct(id);
        return "Товар с ID " + id + " был удален";
    }

    @PutMapping("/catalog/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product product) {
        shopService.updateProduct(id, product);
        return "Товар обновлен!";
    }
    // Пример запроса: http://localhost:8080/catalog/search?limit=5000
    @GetMapping("/catalog/search")
    public List<Product> searchProducts(@RequestParam int limit) {
        return shopService.getProductsCheaperThan(limit);
    }

}