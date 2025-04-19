# 🛒 Spring Boot E-Commerce Website

A full-featured E-commerce web application developed using Spring Boot. This project includes user authentication, product browsing, cart management, order placement, and admin dashboard functionalities.

## 📌 Features

- User registration and login (Spring Security)
- Role-based authorization (ADMIN / USER)
- Product listing by category
- Shopping cart and checkout system
- Order management for users
- Admin dashboard to manage products, orders, accounts, and more
- Responsive design using Bootstrap
- RESTful APIs for data communication

## 🛠 Technologies Used

### 🔧 Backend:
- Java
- Spring Boot (Spring MVC, Spring Security, Spring Data JPA)
- Hibernate
- RESTful API

### 🎨 Frontend:
- Bootstrap
- AngularJS (in progress)
- Thymeleaf
- jQuery

### 💾 Database:
- SQL Server

### 🧰 Tools:
- Spring Tool Suite (STS)
- Postman (API Testing)
- Git, GitHub

## 📚 How to Use

To get started with the Spring Boot Online Shopping Website, follow the steps below:

1. **📥 Clone the Repository**

   ```bash
   git clone https://github.com/baovirus/Spring_Boot_Online_Shopping_Website.git

2. 🛠 Set Up the Database
- Open SQL Server Management Studio (or your preferred SQL tool)
- Run the SQL script located at: /src/main/resource/static/db/J6Store.sql
- This will create and populate the necessary database schema.

3. ⚙️ Configure Application Properties (optional)
- Navigate to the file: /src/main/resources/application.properties
- Update the following configurations with your SQL Server credentials:
"
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=YourDatabaseName
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
"
4. ▶️ Run the Application
- Open the project in Spring Tool Suite, IntelliJ, or Eclipse
- Run project

5. 🌐 Access the Web App
- Open your browser and go to: "http://localhost:8082/"

  **That's it! You're now ready to explore the features of the shopping website.**
