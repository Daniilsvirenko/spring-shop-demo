-- Пароль "pass" зашифрованный в BCrypt
UPDATE app_user SET password = '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRkgVduWkyXMtrF.qqi/jAAg7d.' WHERE username = 'admin';
UPDATE app_user SET password = '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRkgVduWkyXMtrF.qqi/jAAg7d.' WHERE username = 'user';