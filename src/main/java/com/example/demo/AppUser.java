package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user") // Указываем точное имя таблицы из SQL
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Логины не должны повторяться
    private String username;

    private String password;

    private String role; // Например: "ADMIN", "USER"

    // Обязательный пустой конструктор для Hibernate
    public AppUser() {}

    public AppUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- Геттеры и Сеттеры (можно сгенерировать Alt+Insert) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
