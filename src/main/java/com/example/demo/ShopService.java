package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ProductRepository repository;

    public ShopService() {}

    public List<Product> getProductList() {
        return repository.findAll();
    }

    public void saveProduct(Product product) {
        repository.save(product);
    }

    // 1. Найти один товар по ID
    public Product getProductById(Long id) {
        // orElse(null) значит: "если товар не найден, верни null"
        // (в будущем мы будем выбрасывать тут ошибку, но пока так проще)
        return repository.findById(id).orElse(null);
    }

    // 2. Удалить товар
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    // Метод обновления
    public void updateProduct(Long id, Product newProductData) {
        // 1. Ищем, есть ли такой товар вообще?
        Product existingProduct = repository.findById(id).orElse(null);

        if (existingProduct != null) {
            // 2. Если нашли — меняем данные в СТАРОМ объекте на НОВЫЕ
            existingProduct.setName(newProductData.getName());
            existingProduct.setPrice(newProductData.getPrice());

            // 3. Сохраняем.
            // Хитрость JPA: так как у existingProduct уже есть ID,
            // метод save() сделает SQL UPDATE, а не INSERT.
            repository.save(existingProduct);
        }
    }
    public List<Product> getProductsCheaperThan(int price) {
        return repository.findByPriceLessThan(price);
    }

}
