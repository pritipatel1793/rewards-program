# Spring Boot Rewards Calculator

A Spring Boot application that calculates reward points for customer transactions based on the following rules:
- 2 points for every dollar spent over $100 in each transaction
- 1 point for every dollar spent between $50 and $100 in each transaction

## Features

- RESTful API endpoints for customer and transaction management
- H2 in-memory database for data storage
- Exception handling with custom exceptions
- Lombok for reducing boilerplate code
- Swagger UI for API documentation

## API Endpoints

### Customer Endpoints
- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get customer by ID
- `POST /api/customers` - Create a new customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer

### Rewards Endpoints
- `GET /api/rewards/customer/{id}/total` - Get total rewards points for a customer
- `GET /api/rewards/customer/{id}/monthly` - Get monthly rewards points for a customer

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## Getting Started

1. Clone the repository
2. Make sure you have Java 17 and Maven installed
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at `http://localhost:8080`
5. Access H2 Console at `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:rewardsdb`
   - Username: `SA`
   - Password: (leave empty)

## Testing the API

You can test the API endpoints using curl or any API testing tool like Postman. Example:

```bash
# Get all customers
curl http://localhost:8080/api/customers

# Get total rewards for a customer
curl http://localhost:8080/api/rewards/customer/1/total

# Get monthly rewards for a customer
curl http://localhost:8080/api/rewards/customer/1/monthly
```

## Project Structure

```
src/main/java/com/rewards/
├── RewardsCalculatorApplication.java
├── controller/
│   └── RewardsController.java
├── model/
│   ├── Customer.java
│   └── Transaction.java
├── repository/
│   ├── CustomerRepository.java
│   └── TransactionRepository.java
├── service/
│   └── RewardsService.java
├── dto/
│   ├── CustomerRewardPoints.java
│   └── MonthlyRewardPoints.java
└── config/
    └── DataInitializer.java
```

## Development

The application uses:
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5
- Mockito

## License

This project is licensed under the MIT License. 