package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Тот самый BCrypt из SecurityConfig

    // 1. Показать форму регистрации
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    // 2. Обработать нажатие кнопки "Зарегистрироваться"
    @PostMapping("/register")
    public String registerUser(AppUser user) {
        // Проверка: есть ли такой юзер?
        if (appUserRepository.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/register?error"; // Если занято — ошибка
        }

        // ВАЖНО: Шифруем пароль перед сохранением!
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Выдаем права обычного пользователя
        user.setRole("USER");

        // Сохраняем в базу
        appUserRepository.save(user);

        return "redirect:/login"; // Перекидываем на вход
    }

    // Страница входа (если вы захотите кастомную, но пока используем дефолтную)
    @GetMapping("/login")
    public String login() {
        return "login"; // Создадим этот файл ниже, если хотим красивый логин
    }
}