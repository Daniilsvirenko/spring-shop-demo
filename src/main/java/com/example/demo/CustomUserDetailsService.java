package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    // Внедряем наш репозиторий
    public CustomUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Ищем пользователя в нашей базе
        AppUser appUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 2. Превращаем нашего AppUser (из базы) в UserDetails (который понимает Security)
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword()) // Хэш пароля из базы
                .roles(appUser.getRole())        // Роль ("ADMIN" или "USER")
                .build();
    }
}