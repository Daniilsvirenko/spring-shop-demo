package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // 1. Разрешаем СМОТРЕТЬ (/store) и страницу "О нас" всем
                        .requestMatchers(HttpMethod.GET, "/store").permitAll()
                        .requestMatchers("/about").permitAll()

                        // 2. ДОБАВЛЯТЬ (POST /store) и УДАЛЯТЬ могут только АДМИНЫ
                        .requestMatchers(HttpMethod.POST, "/store").hasRole("ADMIN")
                        .requestMatchers("/store/delete/**").hasRole("ADMIN")

                        // 3. Всё остальное — под замок
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .permitAll()
                        .defaultSuccessUrl("/store", true) // <--- ВОТ ЭТО ЧИНИТ ПЕРЕАДРЕСАЦИЮ!
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/store") // После выхода тоже вернемся в магазин
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Создаем пользователя USER
        UserDetails user = User.withDefaultPasswordEncoder() // Временно (для тестов)
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        // Создаем пользователя ADMIN
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        // Храним их в оперативной памяти (пока без базы данных)
        return new InMemoryUserDetailsManager(user, admin);
    }
}