package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Обратите внимание: не RestController!
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FrontendController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/store") // Адрес страницы в браузере
    public String showStorePage(Model model) {
        // 1. Получаем список товаров из сервиса
        var productList = shopService.getProductList();

        // 2. Кладем его в "посылку" (Model) под названием "products"
        // Это имя "products" мы используем в HTML файле в th:each
        model.addAttribute("products", productList);

        // 3. Возвращаем имя HTML-файла (без .html)
        return "products";
    }

    @PostMapping("/store")
    public String addNewProduct(Product product) {
        // 1. Сохраняем товар (Spring сам создал объект product из полей формы)
        shopService.saveProduct(product);

        // 2. Команда "Redirect": говорим браузеру "Обнови страницу /store"
        // Это нужно, чтобы новый товар сразу появился в списке
        return "redirect:/store";
    }
    @PostMapping("/store/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        shopService.deleteProduct(id);
        return "redirect:/store";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about"; // Вернет about.html
    }
}