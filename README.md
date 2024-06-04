# Fitsphere Backend

Welcome to the backend repository of the Fitsphere application, a comprehensive fitness management system built with Spring Boot. This repository contains all the server-side code and configurations necessary to run the Fitsphere backend services.

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Key Concepts](#key-concepts)
- [Testing](#testing)
- [CI/CD](#ci/cd)
- [Contributing](#contributing)
- [License](#license)

## Project Description

Fitsphere is a fitness management platform designed to help users track their workouts, manage their fitness goals, and connect with trainers. The backend services handle all the business logic, data processing, and API interactions for the Fitsphere platform.

## Features

- User authentication and authorization
- Workout management
- Trainer and client interactions
- Appointment scheduling
- Rating and review system
- Analytics and reporting

## Technology Stack

- **Java**: Programming language
- **Spring Boot**: Framework for building the backend services
- **Spring Security**: Security framework for authentication and authorization
- **Hibernate/JPA**: ORM for database interactions
- **MySQL**: Relational database
- **Gradle**: Build and dependency management tool
- **Lombok**: Reduces boilerplate code for model objects
- **JaCoCo**: Code coverage tool
- **SonarQube**: Continuous code quality inspection tool
- **Docker**: Containerization platform

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed on your system:

- Java 11 or higher
- Gradle 6.8 or higher
- MySQL
- Docker
- SonarQube (for code quality inspection)

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/fitsphere-backend.git
    cd fitsphere-backend
    ```

2. **Install dependencies:**

    ```bash
    gradle clean build
    ```

### Configuration

1. **Database Configuration:**

   Update the database configuration in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/fitsphere
    spring.datasource.username=your-username
    spring.datasource.password=your-password

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    ```

2. **Application Configuration:**

   You can configure other application properties as needed in the `application.properties` file.

### Running the Application

1. **Run the application using Gradle:**

    ```bash
    gradle bootRun
    ```

2. **Alternatively, build the JAR file and run it:**

    ```bash
    gradle clean build
    java -jar build/libs/fitsphere-backend-0.0.1-SNAPSHOT.jar
    ```

### API Endpoints

The API endpoints for the Fitsphere application are documented using Swagger. Once the application is running, you can access the API documentation at:

http://localhost:8080/swagger-ui.html


## Key Concepts

### REST APIs

Fitsphere follows REST (Representational State Transfer) principles to build its APIs. REST is an architectural style for designing networked applications and is based on a stateless, client-server communication protocol—usually HTTP. In this application, we have implemented REST APIs to manage users, workouts, appointments, and more. Each API endpoint corresponds to a specific functionality and interacts with the data model via HTTP methods such as GET, POST, PUT, and DELETE.

### SOLID Principles

To ensure our codebase is maintainable, scalable, and robust, we adhere to SOLID principles:

- **Single Responsibility Principle (SRP)**: Each class has a single responsibility. For instance, our services handle business logic, while controllers manage HTTP requests.
- **Open/Closed Principle (OCP)**: Our code is open for extension but closed for modification. We achieve this by using interfaces and abstract classes.
- **Liskov Substitution Principle (LSP)**: Subtypes must be substitutable for their base types. Our inheritance hierarchy respects this principle.
- **Interface Segregation Principle (ISP)**: We use client-specific interfaces to avoid implementing methods that are not required.
- **Dependency Inversion Principle (DIP)**: High-level modules do not depend on low-level modules; both depend on abstractions. We use dependency injection to achieve this.

### Security

Security is paramount in Fitsphere. We use Spring Security to handle authentication and authorization, ensuring that users have access only to the resources they are permitted to. We implement role-based access control and secure our APIs against common vulnerabilities such as CSRF and XSS.

### Persistence

We use Hibernate ORM with JPA to manage database interactions. This allows us to map Java objects to database tables seamlessly and perform CRUD operations efficiently. Our database schema is managed through JPA annotations and configurations.

### Lombok

Lombok is used to reduce boilerplate code in our model classes. By using annotations such as `@Data`, `@Builder`, and `@NoArgsConstructor`, we can automatically generate getters, setters, and other utility methods, keeping our code clean and readable.

### Code Quality and Coverage

We use JaCoCo to measure code coverage of our tests. This ensures that our code is thoroughly tested and reliable. The coverage reports are integrated with SonarQube to provide continuous inspection of code quality, detecting bugs, code smells, and security vulnerabilities.

## Testing

We have comprehensive unit and integration tests to ensure the reliability of our application. We use JUnit for testing and have written tests for our services, controllers, and repositories.

To run the tests and generate the JaCoCo report, use the following Gradle command:

```bash
gradle test jacocoTestReport
