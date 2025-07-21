# Micro Demo Project

This repository contains multiple microservices built using **Java**, **Spring Boot**, and **Eureka Service Discovery**. Each project in this repo represents a different service in the system architecture.

## 📁 Project Structure

- `Product-Service`: Handles product-related operations and endpoints.
- `Order-Service`: Manages order placement, retrieval, and database operations.
- `API-Gateway-Service`: Serves as the routing and filtering layer using Spring Cloud Gateway.
- `Eureka-Service`: Service registry for discovering other microservices.

## 🚀 How to Run

1. Start the **Eureka-Service**
2. Launch **Product-Service** and **Order-Service**
3. Start the **API-Gateway-Service**
4. Access endpoints via:  
   - `http://localhost:8000/products`  
   - `http://localhost:8000/orders`

Make sure each service is registered with Eureka correctly.

## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Cloud Gateway
- Spring Data JPA
- Eureka Discovery Server
- Maven

## 📦 Requirements

- Java SDK installed
- Eclipse IDE
- Git
- MySQL or H2 database (optional, if you're using database operations)

## 📝 Notes

- All services are set to run on different ports.
- You can modify application properties for custom setups.

## 🧑‍💻 Author

Created by **prakash1007**

---
