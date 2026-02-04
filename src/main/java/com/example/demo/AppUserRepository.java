package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // Магия Spring Data: он сам напишет SQL запрос "SELECT * FROM app_user WHERE username = ?"
    Optional<AppUser> findByUsername(String username);
}