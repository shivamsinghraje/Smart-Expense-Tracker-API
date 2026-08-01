# Smart Expense Tracker API

A RESTful Expense Tracker API made with **Java 21** and **Spring Boot**. This app allows users to keep track of their personal expenses by providing REST endpoints to add, view, filter, calculate totals, and delete expenses.

This project was developed as keeping the application lightweight and focused on REST API design, all expense data is stored in memory using a Java List, and no database is used.

---

## Features

- Add a new expense
- View all expenses
- Filter expenses by category
- Calculate total expenses
- Calculate total expenses by category
- Delete an expense
- Global exception handling
- Interactive API documentation using Swagger/OpenAPI
- Unit and controller tests

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Web (Spring MVC)
- Maven
- JUnit 5
- MockMvc
- Springdoc OpenAPI (Swagger UI)

---

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/shivam/expensetracker/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── exception/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── ExpenseTrackerApplication.java
│   └── resources/
└── test/
```

### Package Overview

| Package | Responsibility |
|----------|----------------|
| controller | Handles incoming HTTP requests and returns API responses |
| service | Contains business logic |
| repository | Manages in-memory expense storage |
| model | Defines the Expense model |
| exception | Handles custom exceptions and global error handling |
| config | OpenAPI / Swagger configuration |

---

# Installation & Running the Project

## Prerequisites

- Java 21 or later
- Maven 3.9+

---

## Clone the repository

```bash
git clone https://github.com/shivamsinghraje/Smart-Expense-Tracker-API
```

---

## Navigate to the project

```bash
cd Smart-Expense-Tracker-API
```

---

## Install dependencies

```bash
mvn clean install
```

---

## Run the application

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

---

# Running the Tests

Execute all unit and controller tests using:

```bash
mvn test
```

---

# Swagger / OpenAPI Documentation

Once the application is running, open the following URL in your browser:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger UI provides interactive documentation for all available REST endpoints and allows testing the API directly from the browser.

---

# API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/expenses` | Add a new expense |
| GET | `/expenses` | Retrieve all expenses |
| GET | `/expenses/category/{category}` | Retrieve expenses by category |
| GET | `/expenses/total` | Calculate total expenses |
| GET | `/expenses/total/{category}` | Calculate total expenses for a category |
| DELETE | `/expenses/{id}` | Delete an expense |

---

# Example Request

**POST** `/expenses`

```json
{
  "title": "Book",
  "amount": 500,
  "category": "Education",
  "date": "2026-08-01"
}
```

---

# Example Response

```json
{
  "id": 1,
  "title": "Book",
  "amount": 500,
  "category": "Education",
  "date": "2026-08-01"
}
```

---

# Example Error Response

If a requested expense does not exist, the API returns an appropriate error response.

```json
{
  "timestamp": "2026-08-01T12:45:30",
  "status": 404,
  "message": "Expense not found"
}
```

---

# Design Decisions

- Data is stored in an in-memory `List<Expense>` 
- The project follows a layered architecture:
    - Controller
    - Service
    - Repository
- Global exception handling is implemented for consistent API responses.
- Swagger/OpenAPI is integrated for interactive API documentation.

---

# Future Improvements

- Persist data using MySQL or PostgreSQL
- Add user authentication and authorization
- Implement pagination and sorting
- Add expense analytics and monthly summaries
- Dockerize the application for easier deployment

---

