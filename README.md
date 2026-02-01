# 🛍️ Spring Boot Fullstack Shop

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Frontend-darkgreen?style=for-the-badge&logo=thymeleaf)

A robust full-stack inventory management application built with **Spring Boot** and **PostgreSQL**.
This project demonstrates a classic **MVC architecture**, secure database connectivity, and server-side rendering using **Thymeleaf**.

It was built to master the core concepts of Enterprise Java Development: dependency injection, ORM (Hibernate), and RESTful API design.

---

## 🚀 Features

* **Product Management (CRUD):** Create, Read, Update, and Delete products directly via the web interface.
* **Data Persistence:** Uses **PostgreSQL** for production-grade data storage (migrated from H2 in-memory DB).
* **Smart Filtering:** Custom SQL queries via Spring Data JPA to filter products by price.
* **Interactive UI:** Server-side rendering with **Thymeleaf** for dynamic HTML content.
* **Secure Configuration:** Database credentials managed via Environment Variables (12-Factor App methodology).

---

## 🛠️ Tech Stack

* **Backend:** Java 21, Spring Boot 3 (Web, Data JPA)
* **Database:** PostgreSQL (Production), H2 (Testing)
* **Frontend:** Thymeleaf, HTML5, CSS
* **Build Tool:** Maven
* **Version Control:** Git & GitHub

---

## ⚙️ How to Run Locally

### Prerequisites
* Java 21 SDK
* PostgreSQL installed and running
* Maven

### 1. Clone the repository
```bash
git clone [https://github.com/Daniilsvirenko/spring-shop-demo.git](https://github.com/Daniilsvirenko/spring-shop-demo.git)
cd spring-shop-demo
2. Configure Database
Create a database named shop_db in PostgreSQL.

Security Note: This project uses Environment Variables to hide sensitive passwords. You need to set the DB_PASSWORD variable in your IDE or terminal before running.

IntelliJ IDEA: Run Configuration -> Environment Variables -> DB_PASSWORD=your_real_password

Terminal: export DB_PASSWORD=your_real_password

3. Run the App
Bash
./mvnw spring-boot:run
Access the application at: http://localhost:8080/store

🛣️ Roadmap & Future Improvements
[ ] Docker: Containerize the application and database with Docker Compose.

[ ] Spring Security: Add login/registration functionality.

[ ] Flyway: Implement database migration versioning.

[ ] Testing: Increase Unit Test coverage with JUnit 5 and Mockito.

👨‍💻 Author
Daniil Svirenko Computer Science Student @ FH Campus Wien
