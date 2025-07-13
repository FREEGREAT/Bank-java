# MicroserviceBank - Accounts Microservice

## Description
Initial implementation of Accounts microservice with basic project structure and documentation

## Used Technologies
- **Java 21**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **Maven**
- **H2 Database** 
- **Lombok**

## API Endpoints

| Method | Endpoint | Description                          |
|--------|--|--------------------------------------|
| POST   | /api/account | Create a new account                 |
| GET    | /api/account?mobileNumber= | Get account details by mobile number |
| PUT    | /api/account | Update account info                  |
| DELETE | /api/account?mobileNumber=  | Delete account                       |

> For detailed request/response formats, refer to the controller and DTO classes.

## Example `application.properties`

```
# Server port
server.port=8080

# H2 Database (default)
spring.datasource.url=jdbc:h2:mem:accountsdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

# Logging
logging.level.org.springframework=INFO
```

## Getting Started

1. **Clone the repository:**
   ```bash
   cd MicroserviceBank/accounts
   ```
2. **Build the project:**
   ```bash
   ./mvnw clean install
   ```
3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```



#