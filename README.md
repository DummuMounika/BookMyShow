# BookMyShow - Microservices Project 🎬

## Overview  
BookMyShow is a microservices-based platform that simplifies movie ticket bookings. The project is designed for scalability and security, leveraging **Spring Boot** and **Docker**. This README provides an overview of the platform's architecture, deployment, and key technologies used.

## 🏗️ Microservices Architecture
BookMyShow follows a modular microservices approach, enabling flexibility and high availability. The core microservices include:

- **User Service**: Manages authentication and user profiles.
- **Admin Service**: Manages all entities entry
- **Search Service**: Handles movie, theater, show, and  seat details and schedules.
- **Booking Service**: Processes ticket bookings and payments.
- **Payment Service**: Ensures secure and seamless transactions.
- **Notification Service**: Sends booking confirmations and alerts.
- **Eureka Discovery Service**: Enables service discovery.
- **API Gateway**: Acts as a central entry point for client requests.
- **Spring Config Server**: Manages Common Configuration.

## 🛠️ Infrastructure
- **Spring Cloud Config Server**: Centralized configuration management.
- **Netflix Eureka Naming Server**: Facilitates service discovery.
- **API Gateway**: Routes client requests to the respective services.
- **Feign Client**: Simplifies inter-service communication.
- **Circuit Breaker (Resilience4J)**: Improves fault tolerance by preventing cascading failures.
- **Rate Limiting**: Protects the system from excessive API requests.
- **Retry Mechanism (Resilience4J)**: Enhances API reliability by retrying failed requests.
- **Zipkin Distributed Tracing Server**: Provides request tracing across services.
- **PostgreSQL Database**: Ensures efficient data storage and retrieval.
- **ELK Stack**: Monitoring & Logging

## 🚀 Deployment
BookMyShow supports deployment in various environments with the following technologies:

- **Containerization**: Docker ensures consistency across environments.

## 📌 API Endpoints
### 🎭 User Service (USER-SERVICE)
- `GET /api/userDetails?userId= - Retrieve user detail
- `POST /api/register` - Register a new user
- `POST /api/login` - Authenticate a user

### 🎭 Admin Service (ADMIN-SERVICE)
- `POST /api/addShow = - Adds new show
- `POST /api/addShowSeatDetails` - Adds new seats for shows

### 🔍Search Service (SEARCH-SERVICE)
- `GET /api/cities` - Retrieve all cities
- `GET /api/v2/cities` - Retrieve all cities with names and IDs
- `GET /api/movie/search?movieName=` - Search Movies based on movie name
- `GET /api/v2/movie/search?movieName=` - Search Movies based on movie names and IDs
- `GET /api/showdetails?showId=` - Retrieve Show details by show ID
- `GET /api/theater/seats?showId=` - Retrieve Show seat details by show ID
- `POST /api/shows/search?movieName=City=` - Search Theaters based on movie name and city
- `POST /api/v2/shows/search?movieName=City=` - Search Theaters based on movie and city IDs

### 🎟️ Booking Service (BOOKING-SERVICE)
- `POST /api/addBooking` - Book movie tickets
- `PATCH api/booking/changeseatstatus?seatUniqueIds=&status=&showId=` - Change Seat status from Available->Pending->Booked
- `POST /api/booking/summary` - Retrieve Booking summary

### 💳 Payment Service (PAYMENT-SERVICE)
- `POST api/payment` - Process a payment

### 🔔 Notification Service (NOTIFICATION-SERVICE)
- `POST api/sendNotification` - Send notifications

## 🎯 Setup & Installation
### Prerequisites
- **Docker & Docker-Compose** installed
- **Java 17+**
- **PostgreSQL**

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/DummuMounika/BookMyShow.git
   cd BookMyShow
   ```
2. Start the services using Docker Compose:
   ```bash
   docker-compose up --build
   ```
3. Access the services:
   - **Eureka Dashboard**: `http://localhost:8761`
   - **API Gateway**: `http://localhost:8080`
   - **PostgreSQL Admin**: `http://localhost:5432`
   - **Log Monitoring**: `http://localhost:5601`


