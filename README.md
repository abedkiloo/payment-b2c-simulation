# Mobile Payment b2c API

## Overview
The **Mobile Payment API** is a Spring Boot application that facilitates **mobile money transactions**.  
It allows users to:
- **Initiate payments**
- **Check transaction statuses**
- **Retry failed transactions**
- **View their transaction history**

The system integrates with **mobile money providers** (e.g., **M-Pesa, Airtel Money**) and sends **real-time SMS notifications** for payment updates.

---

## Features
- Initiate mobile money payments  
- Check transaction status  
-  Retry failed transactions  
-  View transaction history  
-  Integration with mobile money providers  
-  SMS notifications for payment updates

---

## Technology Stack
- **Spring Boot** – Backend framework
- **Spring Data JPA** – ORM for database operations
- **MySQL** – Relational database
- **Hibernate** – ORM implementation
- **Lombok** – Reduces boilerplate code
- **Maven** – Dependency management
- **Postman** – API testing tool

---

## Project Structure
```bash
payment-b2c/
│── src/
│   ├── main/
│   │   ├── java/com/abedkiloo/
│   │   │   ├── controller/            # REST Controllers
│   │   │   ├── dto/                   # Data      Transfer Objects
│   │   │   ├── model/                 # EntityModels
│   │   │   ├── repository/            # DataRepository Layer
│   │   │   ├── service/               # Business  Logic Layer
│   │   │   ├── Main.java  
│   ├── resources/
│   │   ├── application.properties     # Application Configuration
│── pom.xml                             # Project Dependencies
│── README.md                           # Project Documentation


# Getting Started

## 1. Prerequisites
Before running this project, ensure you have installed:

-  JDK 17+  
-  Maven
-  MySQL
-  Postman *(for testing API requests)*  

---

## 2. Setup Database

### Create a MySQL database:
```sql
CREATE DATABASE payment_db;

Update src/main/resources/application.properties with your database credentials:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/payment_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
3. Install Dependencies
Run the following command inside the project folder:

mvn clean install
4. Running the Application
Run the Spring Boot application using:

mvn spring-boot:run
Or, inside IntelliJ IDEA, open MobilePaymentApplication.java and click Run ▶.

API Endpoints
1. Initiate Payment
🔹 Endpoint: POST /api/payment
🔹 Request Body:


{
  "phoneNumber": "+254712345678",
  "amount": 500,
  "provider": "MPESA"
}
🔹 Response:


{
  "id": 1,
  "phoneNumber": "+254712345678",
  "amount": 500,
  "status": "PENDING",
  "provider": "MPESA"
}
2. Check Transaction Status
🔹 Endpoint: GET /api/payment/status/{transactionId}
🔹 Response:
{
  "transactionId": "abc123",
  "status": "SUCCESS"
}
3. Retry Failed Transaction
🔹 Endpoint: POST /api/payment/retry/{transactionId}
🔹 Response:

{
  "message": "Transaction retried successfully"
}
4. Get Transaction History
🔹 Endpoint: GET /api/payment/history/{phoneNumber}
🔹 Response:
[
  {
    "transactionId": "abc123",
    "phoneNumber": "+254712345678",
    "amount": 500,
    "status": "SUCCESS"
  },
  {
    "transactionId": "xyz789",
    "phoneNumber": "+254712345678",
    "amount": 200,
    "status": "FAILED"
  }
]
Change the default port in application.properties:

properties
server.port=8081
 Database connection issue?
Ensure MySQL is running and check credentials in application.properties.

Run: mvn clean install
Author
👤 Abednego Kilonzo
📧 Email: abednego.k.wambua@gmail.com
📞 Phone: +254704494519